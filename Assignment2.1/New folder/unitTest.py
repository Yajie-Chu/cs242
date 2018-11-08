# -*- coding: utf-8 -*-
"""
Created on Mon Feb 26 15:30:31 2018

@author: aldos
"""

from Graph import Graph
from Node import Node
import json

def test_add_node(g, data):
    
    for m in data['Movies']:
        g.add_node(Node(m))
        
    for a in data['Actors']:
        g.add_node(Node(a))
        
def test_add_edge(g, data):
    for m in data['Movies']:
        weight = 0
        title = m['movieTitle']
        for al in m['ActorList']:
            g.add_edge(title, al, weight)
            weight = weight + 1
            
def test_print_edge(g):
    g.print_edgelist()


if __name__ == '__main__':
    with open('data.txt') as json_file:
        data = json.load(json_file)
    g = Graph()
    
    '''
    Test all functions in Graph.py
    '''
    test_add_node(g, data)
    test_add_edge(g, data)
    test_print_edge(g)
   
    g.print_movie_list("bruce willis")
    
    g.print_actor_name("batman begins")
    
    g.print_a_movie_grossed("batman begins")
    
    g.print_oldest_actor(10)
    
    movielist = g.print_all_movies_inayear(2012)
    for ml in movielist:
        print(ml)
        
    g.print_all_actors_inayear(2012)
        