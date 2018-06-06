#include <stdio.h>
#include <stdlib.h>
// Zusätzliche Header-Dateien hier!
 

#include "vorgabe.h"
 
unsigned int bottles; 
chemical_t chemical; 

void init_semaphores()
{
	// *** HIER EUER CODE ***
  for (int i = 0; i < CHEMICAL_NUM; i++)
 {
   bottles = (unsigned int) &chemicals[i].bottles;   
   if(sem_init(&chemical.sem,0,bottles)==-1)
     {
       perror("sem_init"); 
       exit(1);
     }
 }
}

void destroy_semaphores()
{
	// *** HIER EUER CODE ***
  for (int i = 0; i < CHEMICAL_NUM; i++) 
    { 
      if(sem_destroy(&chemical.sem)!=0)
	{
	  perror("sem_destroy");
	  exit(1);
	}
    }
}
