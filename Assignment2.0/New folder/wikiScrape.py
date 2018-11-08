# -*- coding: utf-8 -*-
"""
Created on Sat Feb 24 14:28:12 2018

@author: aldos
"""

from bs4 import BeautifulSoup
from urllib.request import urlopen
import json
import logging

data = {}
data['Movies'] = []
data['Actors'] = []
ActorsLink = []
MoviesLink = []
processedActorsLink = []
processedMoviesLink = []

def startPage(url):
    html = urlopen("https://en.wikipedia.org"+url)
    bsObj = BeautifulSoup(html.read(), "lxml")

    movies = bsObj.find("div", {"class": "div-col columns column-width"}).findAll("li")
    name = bsObj.find("title").get_text().split(" -")[0]
    age = bsObj.find("span",{"class": "noprint ForceAgeToShow"})
    age = age.get_text().replace("(age", "")
    age = age.replace(")", "")

    MovieList = []
    MovieUrl = []
    for m in movies:
        movieTitle = m.find("i").get_text()
        movieYear = m.get_text().split("(")[1]
        movieYear = movieYear.split(")")[0]
        link = m.find("a").attrs['href']
        actors_url, actors_list, gross_val = getMovie("https://en.wikipedia.org" + link)
         
        if len(actors_list) > 2:
            processedMoviesLink.append(link)
            data['Movies'].append({'movieTitle': movieTitle, 'movieYear':movieYear, 'movieGross': gross_val,'actorUrl':actors_url,'ActorList': actors_list})
        MovieList.append(movieTitle)
        MovieUrl.append(link)

    data['Actors'].append({'ActorName': name, 'ActorAge':age, 'MoviesUrl':MovieUrl,'Movies': MovieList})


def getMovie(url):
    html = urlopen(url)
    bsObj = BeautifulSoup(html.read(), "lxml")
    flag = False

    actors = bsObj.find("table", {"class":"infobox vevent"}).findAll("tr")
    gross = bsObj.find("table", {"class" : "infobox vevent"}).findAll("tr")
    actors_name_list = []
    actors_url = []
    gross_val = ""
    i = 0
    for g in gross:
        if i == len(gross)-1:
            try:
                gross_val = g.get_text().split("$")[1]
                gross_val = gross_val.split("[")[0]
            #print(gross_val)
            except:
                pass
        i = i + 1
    for a in actors:

        try:
            if a.find("th").get_text() == "Starring":
                starring = a.find("div", {"class":"plainlist"}).findAll("li")
                for s in starring:
                    actors_name_list.append(s.find("a").attrs['title'])
                    actors_url.append(s.find("a").attrs['href'])
                    ActorsLink.append(s.find("a").attrs['href'])
                    #print(s.find("a").attrs['title'])
            flag = True
        except:
            pass
    if flag == True:
        return actors_url, actors_name_list, gross_val
    else:
        return [], [], gross_val


def goThroughActorsLink():
    
  flag = False
  ActorsLink_clone = ActorsLink
  if len(processedActorsLink) < 300 or len(processedMoviesLink) < 150:
      for aL in set(ActorsLink_clone):
          flag = False
          if aL in processedActorsLink:
              continue
          if len(processedActorsLink) > 251:
              return
          try:
              html = urlopen("https://en.wikipedia.org" + aL)
              bsObj = BeautifulSoup(html.read(), "lxml")
              filmTable = bsObj.find("span", {"id": "Filmography"}).parent.next_sibling
              age = bsObj.find("span",{"class": "noprint ForceAgeToShow"})
              age = age.get_text().replace("(age", "")
              age = age.replace(")", "")
              name = bsObj.find("title").get_text().split(" -")[0]
              MovieList = []
              MovieUrl = []
              flaging = True
              
              try:
                  filmTable.next_sibling.find("a").attrs['href']
                  #print(filmTable.next_sibling.find("a").attrs['href'])
                  html2 = urlopen("https://en.wikipedia.org" + filmTable.next_sibling.find("a").attrs['href'])
                  bsObj2 = BeautifulSoup(html2.read(), "lxml")
                  try:
                      ft = bsObj2.find("span", {"id": "Films"}).parent
                      while ft.find("tr") is None or ft == "\n":
                          ft = ft.next_sibling

                      for f in ft:
                          try:
                              movieYear = f.find("td").get_text()
                              #print(movieYear)
                          except:
                              pass
                          try:
                              movieTitle = f.find("i")
                              movieTitle = f.find("a").attrs['title']
                              movieLink = f.find("a").attrs['href']
                              MovieList.append(movieTitle)
                              MovieUrl.append(movieLink)
                              if movieLink not in processedMoviesLink and len(processedMoviesLink) < 126:
                                  actor_url, actors_list, gross_val = getMovie("https://en.wikipedia.org" + movieLink)
                                   
                                  if len(actors_list) > 2:
                                      data['Movies'].append({'movieTitle': movieTitle, 'movieYear':movieYear, 'movieGross': gross_val,'actorUrl':actor_url,'ActorList': actors_list})
                                      
                                      processedMoviesLink.append(movieLink)
                                      print(name+ " "+movieLink)
                                      #print("titel is", movieTitle)
                          except:
                              pass
                      if len(MovieList) > 2:
                          data['Actors'].append({'ActorName': name, 'ActorAge':age,'MovieUrl':MovieUrl, 'Movies': MovieList})
                          flaging = False
                  except:
                      pass

                  try:
                      ft = bsObj2.find("span", {"id": "Film"}).parent
                      while ft.find("tr") is None or ft == "\n":
                          ft = ft.next_sibling

                      for f in ft:
                          try:
                              movieYear = f.find("td").get_text()
                              #print(movieYear)
                          except:
                                pass
                          try:
                              movieTitle = f.find("i")
                              movieTitle = f.find("a").attrs['title']
                              movieLink = f.find("a").attrs['href']
                              MovieList.append(movieTitle)
                              MovieUrl.append(movieLink)
                              if movieLink not in processedMoviesLink and len(processedMoviesLink) < 126:
                                  actor_url, actors_list, gross_val = getMovie("https://en.wikipedia.org" + movieLink)
                                   
                                  if len(actors_list) > 2:
                                      processedMoviesLink.append(movieLink)
                                      data['Movies'].append({'movieTitle': movieTitle, 'movieYear':movieYear, 'movieGross': gross_val,'actorUrl':actor_url,'ActorList': actors_list})
                                   
                                      print(name+ " "+movieLink)
                                      #print("titel is", movieTitle)
                          except:
                                 pass

                      if len(MovieList) > 2:
                           data['Actors'].append({'ActorName': name, 'ActorAge':age,'MovieUrl':MovieUrl,'Movies': MovieList})
                           flaging = False
                  except:
                        pass
              except:
                  pass

              if flaging == False:
                    processedActorsLink.append(aL)
                    continue

              while filmTable.find("tr") is None or filmTable == "\n":
                  filmTable = filmTable.next_sibling

              if filmTable.find("div") is not None:
                  filmTable = bsObj.find("table", {"class":"wikitable"})

              for f in filmTable:
                  if f.find("td") is None or f.find("td") == -1:
                      continue
                  else:
                      movieYear = f.find("td").get_text()
                      #print(movieYear)
                      title = f.find("td").next_sibling

                  if title == "\n":
                      title = title.next_sibling
                      try:
                          movieTitle = title.find("a").attrs['title']
                          MovieList.append(movieTitle)
                          movieLink = f.find("a").attrs['href']
                          MovieUrl.append(movieLink)
#                          if movieLink not in processedMoviesLink:
#                              actors_list, gross_val = getMovie("https://en.wikipedia.org" + movieLink)
#                              
#                              if len(actors_list) > 2:
#                                  processedMoviesLink.append(movieLink)
#                                  data['Movies'].append({'movieTitle': movieTitle, 'movieYear':movieYear, 'movieGross': gross_val,'ActorList': actors_list})
                        #print(movieTitle)
                      except:
                          continue
              if len(MovieList) > 2:
                flag = True
                data['Actors'].append({'ActorName': name, 'ActorAge':age, 'MovieUrl':MovieUrl,'Movies': MovieList})
          except:
                print("https://en.wikipedia.org" + aL)
                pass

          if flag == True:
              processedActorsLink.append(aL)
              continue
          
          try:
              html = urlopen("https://en.wikipedia.org" + aL)
              bsObj = BeautifulSoup(html.read(), "lxml")
              filmTable = bsObj.find("span", {"id": "Filmography"}).parent
              age = bsObj.find("span",{"class": "noprint ForceAgeToShow"})
              age = age.get_text().replace("(age", "")
              age = age.replace(")", "")
              name = bsObj.find("title").get_text().split(" -")[0]
              MovieList = []
              MovieUrl = []
              while filmTable.find("li") is None or filmTable == "\n":
                  filmTable = filmTable.next_sibling

              filmTable = filmTable.findAll("li")
              for f in filmTable:
                  try:
                      movieTitle = f.find("a").attrs['title']
                      movieYear = f.get_text().split("(")[1]
                      movieYear = movieYear.split(")")[0]
                      movieLink = f.find("a").attrs['href']
#                      if movieLink not in processedMoviesLink:
#                          actors_list, gross_val = getMovie("https://en.wikipedia.org" + movieLink)
#                           
#                          if len(actors_list) > 2:
#                              processedMoviesLink.append(movieLink)
#                              data['Movies'].append({'movieTitle': movieTitle, 'movieYear':movieYear, 'movieGross': gross_val,'ActorList': actors_list})
                      MovieList.append(movieTitle)
                      MovieUrl.append(movieLink)
                      #print(movieTitle)
                      #print(movieYear)
                  except:
                        pass

              if len(MovieList) > 2:
                   flag = True
                   data['Actors'].append({'ActorName': name, 'ActorAge':age, 'MovieUrl':MovieUrl,'Movies': MovieList})
          except:
              print("https://en.wikipedia.org" + aL)
              pass

          if flag == True:
             processedActorsLink.append(aL)
  else:
      return


if __name__ == '__main__':
    logging.basicConfig(filename='myWikiScrape.log', level=logging.INFO)
    startPage("/wiki/Morgan_Freeman")
    logging.info('Got ActorsLink')
    for i in range(2):
        goThroughActorsLink()
        logging.info('iteration')
    logging.info('Done')
    
    with open('data.txt', 'w') as outfile:
        json.dump(data, outfile)
