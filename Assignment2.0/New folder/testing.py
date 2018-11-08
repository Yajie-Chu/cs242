# -*- coding: utf-8 -*-
"""
Created on Sun Feb 25 00:43:43 2018

@author: aldos
"""
from bs4 import BeautifulSoup
from urllib.request import urlopen
import json
        
    
html = urlopen("https://en.wikipedia.org/wiki/Driving_Miss_Daisy")
bsObj = BeautifulSoup(html.read(), "lxml")


actors = bsObj.find("table", {"class":"infobox vevent"}).findAll("tr")
for a in actors:
    try:
         if a.find("th").get_text() == "Starring":
             starring = a.find("div", {"class":"plainlist"}).findAll("li")
             for s in starring:
                 print("hello")
                 print(s.find("a").attrs['href'])
    except:
        pass
    
#actors_list = actors_list.find("div", {"class":"plainlist"})
#print(actors_list)
#for a in actors_list:
#    try:
#        print(a.find("a").attrs['href'].get_text())
#    except:
#        pass

 

