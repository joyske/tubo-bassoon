#include "4a.h"
#include <stdio.h> /* NULL */
// Hier ggf. weitere Includes

/*
 * Hier könnt ihr ggf. benötigte globale Varibalen und/oder Hilfsfunktionen ablegen
 */

size_t chunksize;
size_t free_chunks = 0;   
size_t first; 
size_t *start; 

//Hilfsfunktion die den Index des ersten passenden freien Blocks zurückgibt 
//wenn es im heap keinen Platz für die angefragte Speichergröße gibt
//wird ein Wert außerhalb des heaps zurückgegeben um einen Fehler zu signalisieren 
size_t FirstFit(size_t request) 
{
	size_t i = 0;
	size_t fehler = NUM_CHUNKS+1; 
	while (i < NUM_CHUNKS) 
	{
		if (allocation_list[i].length < request || allocation_list[i].status == CHUNK_ALLOCATED) 
		{
			i+= allocation_list[i].length; 
		}
		else 
		{
			return i;    
		}    
	}
	return fehler;  
}

void *nf_alloc(size_t size)
{
	//da allocation_list in chunks aufgeteilt ist 
	//brauchen wir auch unsere gewünschte Speichergröße in chunks 
	chunksize = size_to_chunks(size);
	
	//wenn 0 oder etwas größer als unsere heap size angefragt wird 
	//oder es keinen Platz mehr für die Anfrag in unserem heap gibt 
	if (size == 0 || size > sizeof(heap) || FirstFit(chunksize) > NUM_CHUNKS) 
	{
		//... wird die Anfrage ignoriert
		return NULL; 
	}
	else 
	{
		first = FirstFit(chunksize); 
		
		//...ansonsten werden die chunks, in denen unsere Anfrage Platz findet belegt
		for (size_t i = first; i < NUM_CHUNKS; i++) 
		{
			if (i < first+chunksize) 
			{
				allocation_list[i].status = CHUNK_ALLOCATED; 
			}
			else 
			{
				allocation_list[i].status = CHUNK_FREE;
				//wir zählen wie viele freie chunks wir haben 
				free_chunks++; 
			}
		}
		allocation_list[first].length = chunksize;
		//...um für die nächste Anfrage zu wissen wie viel wir noch belegen können  
		allocation_list[first+chunksize].length = free_chunks;  
		
		//die Addresse des chunks an dem wir angefangen haben zu belegen wird zurückgegeben 
		//da er die für uns wichtige Information über die Chunkgröße des belegten blocks enthält
		start = first;
		return start; 
	} 
}
