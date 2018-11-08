# -*- coding: utf-8 -*-
"""

@author: aldos
"""

from Graph import Graph
from Node import Node
import json

def create_nodes(g, data):
    for m in data['Movies']:
        g.add_node(Node(m))
        
    for a in data['Actors']:
        g.add_node(Node(a))
        
def create_edges(g, data):
    for m in data['Movies']:
        weight = 0
        title = m['movieTitle']
        for al in m['ActorList']:
            g.add_edge(title, al, weight)
            weight = weight + 1
                
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
    
def main():   
    
    g = Graph()
    with open('data.txt') as json_file:
        data = json.load(json_file)
        
    create_edges(g, data)
    create_nodes(g, data)
    
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
        print("8. Exit\n")
        
        try:
            num = int(input('Enter your choice: '))
        except ValueError:
            print("WARNING, that's not a number, try again!\n")
            continue
        
        if num == 8:
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
        else:
            break
    
if __name__ == '__main__':
    main()