# -*- coding: utf-8 -*-
"""
@author: aldos
"""

from Node import Node
import heapq
import matplotlib.pyplot as plt
import numpy as np

class Graph(object):
    def __init__(self):
        self.nodelist = []
        self.edgelist = []
       
    '''
    Function that return nodelist
    ''' 
    def get_node(self):
        return self.nodelist
    
    '''
    Function that add the nodes
    ''' 
    def add_node(self, node):
        if node in self.nodelist:
            return
        else:
            self.nodelist.append(node)
    
    
    '''
    Function that add the edges
    ''' 
    def add_edge(self, m, al, weight):
        edge = (m, al, weight)
        for nl in self.nodelist:
            try:
                if nl.node_data['ActorName'] == al:
                    self.edgelist.append(edge)
            except:
                pass
            
    '''
    Function that prints the list of movie an actor has worked in
    @param: actor name
    '''     
    def print_movie_list(self, actor_name):
        
        for nl in self.nodelist:
            try:                
                if nl.node_data['ActorName'].lower() == actor_name:
                    for ml in nl.node_data['Movies']:
                        print(ml)
                    return
            except:
                pass
    '''
    Function that prints the edges
    '''        
    def print_edgelist(self):
        print(len(self.edgelist))
        for el in self.edgelist:
            print(el)
    '''
    Function that prints the actor worked in a movie
    @param: movie name
    '''        
    def print_actor_name(self, movie_name):
    
        for nl in self.nodelist:
            try:
                if nl.node_data['movieTitle'].lower() == movie_name:
                    for al in nl.node_data['ActorList']:
                        print(al)
                    return
            except:
                pass

    '''
    Function that prints the movie gross
    @param: movie name
    '''     
    def print_a_movie_grossed(self, movie_name):
        for nl in self.nodelist:
            try:
                if nl.node_data['movieTitle'].lower() == movie_name:
                    print(nl.node_data['movieGross'])
                    break;
            except:
                pass
            
    '''
    Function that prints the oldest X actor  
    @param: index - the X
    '''           
    def print_oldest_actor(self, index):
        
        ages = []
        for nl in self.nodelist:
            try:
                if int(nl.node_data['ActorAge']):
                    ages.append([int(nl.node_data['ActorAge']), nl.node_data['ActorName']])
            except:
                pass
        print(len(ages))
        for age in heapq.nlargest(index, ages):
            print(age[1])
            
        
    
    '''
    Returns the list of movie in a given year
    @param: year - Given year
    @return: movie list
    '''  
    def print_all_movies_inayear(self, year):
        movielist = []
        for nl in self.nodelist:
            try:
                if int(nl.node_data['movieYear']) == year:
                    movielist.append(nl.node_data['movieTitle'])
            except:
                pass
            
        return movielist
    
    '''
    Function that prints the list of actor in a given year
    @param: year - Given year
    '''          
    def print_all_actors_inayear(self, year):
        
        for nl in self.nodelist:
            try:
                if int(nl.node_data['movieYear']) == year:
                    for al in nl.node_data['ActorList']:
                        print(al)
            except:
                pass
            
    def print_adjacent(self):
        
        sorted_edgelist = []
        
        for nl in self.nodelist:
            try:
                for el in self.edgelist:
                    if nl.node_data['ActorName'] == el[1]:
                        sorted_edgelist.append(el)
            except:
                pass
        
        '''
        Who are the "hub" actors in your dataset? 
        That is, which actors have the most connections with other actors? 
        Two actors have a connection if they have acted in the same movie together.
        '''
        indexes = []
        connection = []
        for el in sorted_edgelist:
             
            try:
            
                for nl in self.nodelist:
                    if el[0] == nl.node_data['movieTitle']:
                        
                        for al in nl.node_data['ActorList']:
                            if el[1] == al:
                                continue
                            
                            if el[1] in indexes:
                                for c in connection:
                                    if c[0] == el[1]:
                                        c[1] = c[1] + 1
                            else:
                                indexes.append(el[1])
                                connection.append([el[1], 1])
                                
            except:
                pass
        connection = sorted(connection, key = lambda tup: tup[1], reverse = True)
        name = []
        score = []
        for c in connection:
            name.append(c[0])
            score.append(c[1])
            
            
        print(connection[0])
        pos = np.arange(len(name))
        
        ax = plt.axes()
        ax.set_xticks(pos)
        ax.set_xticklabels(name)
        plt.xlabel('Actors')
        plt.ylabel('Connection Score')
        plt.bar(pos, score, width = 1.0, color='r')
        plt.show()
        
    def print_gross(self):
        
        indexes = []
        gross_list = []
        for nl in self.nodelist:
            try:
                actor_name = nl.node_data['ActorName']
                for el in self.edgelist:
                    if actor_name == el[1]:
                       
                        if int(nl.node_data['ActorAge']) in indexes:
                            for gl in gross_list:
                                if gl[0] == int(nl.node_data['ActorAge']):
                                    gl[1] = gl[1] + el[2]
                                    gl[1] = round(gl[1], 2)
                        else:
                            indexes.append(int(nl.node_data['ActorAge']))
                            gross_list.append([int(nl.node_data['ActorAge']), el[2]])
                            
            except:
                pass
        gross_list = sorted(gross_list, key = lambda tup: tup[0], reverse = False)  
        ages = []
        gross = []
        for gl in gross_list:
            ages.append(gl[0])
            gross.append(gl[1])
         
        plt.xlabel('Age')
        plt.ylabel('Gross Value')
        
        plt.scatter(ages, gross, c = 'green', alpha = 0.5)
        z = np.polyfit(ages, gross, 1)
        p = np.poly1d(z)
        plt.plot(ages,p(ages),"r--")
        plt.show()
        #print(ages)
        #print(gross)
        
    def print_grossval(self):
        
        for nl in self.nodelist:
            try:
                print(nl.node_data['movieGross'])
            except:
                pass