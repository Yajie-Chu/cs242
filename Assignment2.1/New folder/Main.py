# -*- coding: utf-8 -*-
"""

@author: aldos
"""

from Graph import Graph
from Node import Node
import json
import sys

def create_nodes(g, data):
    for m in data['Movies']:
        g.add_node(Node(m))
        
    for a in data['Actors']:
        g.add_node(Node(a))
        
def create_edges(g, data):
    for m in data['Movies']:
        if m['movieGross'] == "":
            weight = 1000000
        elif int(m['movieGross']) < 200:
            weight = int(m['movieGross']) * 1000000
        else:
            weight = int(m['movieGross'])
        index = 50
        title = m['movieTitle']
        for al in m['ActorList']:
            weight =round(weight/index, 2)
            g.add_edge(title, al, weight )
            index = index + 1
                
def choice_one(g):
    movie_name = input("Please enter the movie name:")
    g.print_a_movie_grossed(movie_name.lower())
    
def choice_two(g):
    actor_name = input("Please enter the actor name:")
    g.print_movie_list(actor_name.lower())
    
def choice_three(g):
     movie_name = input("Please enter the movie name:")
     g.print_actor_name(movie_name.lower())
     
def choice_four(g):
    print("")
    
def choice_five(g):
    index = int(input("Please enter the range index: "))
    g.print_oldest_actor(index)
    
def choice_six(g):
    year = int(input("Please enter the year: "))
    movielist = g.print_all_movies_inayear(year)
    
    for ml in movielist:
        print(ml)
        
def choice_seven(g):
    year = int(input("Please enter the year: "))
    g.print_all_actors_inayear(year)

def choice_eight(g):
    g.print_adjacent()
    
def choice_nine(g):
    g.print_gross()
    
def main():   
    
    g = Graph()
    with open('data.txt') as json_file:
        data = json.load(json_file)
        
    create_nodes(g, data)
    create_edges(g, data)
    
   
    print("Welcome to myFilmography!\n")
    while True:
        print("\nPlease enter 1-7 to output the desire data")
        print("1. Find how much a movie has grossed")
        print("2. List which movies an actor has worked in")
        print("3. List which actors worked in a movie")
        print("4. List the top X actors with the most total grossing value")
        print("5. List the oldest X actors")
        print("6. List all the movies for a given year")
        print("7. List all the actors for a given year")
        print("8. Actors with most connection")
        print("9. Gross vs Age correlation")
        print("10. Exit\n")
    
        
        try:
            num = int(input('Enter your choice: '))
        except ValueError:
            print("WARNING, that's not a number, try again!\n")
            continue
        
        if num == 10:
            print("Thank you, see you next time!")
            break
        elif num == 1:
            choice_one(g)
        elif num == 2:
            choice_two(g)
        elif num == 3:
            choice_three(g)
        elif num == 4:
            choice_four(g)
        elif num == 5:
            choice_five(g)
        elif num == 6:
            choice_six(g)
        elif num == 7:
            choice_seven(g)
        elif num == 8:
            choice_eight(g)
        elif num == 9:
            choice_nine(g)
        else:
            break
    
if __name__ == '__main__':
    main()