#include <stdio.h>
#include <stdlib.h>
// Zusätzliche Header-Dateien hier!

 

#include "vorgabe.h" 

void init_semaphores()
{
	// *** HIER EUER CODE ***
  for (int i = 0; i < CHEMICAL_NUM; i++)
 {   
   if(sem_init((&chemicals[i].sem),0,chemicals[i].bottles)==-1)
     {
       perror("sem_init"); 
       exit(EXIT_FAILURE);
     }
	 printf(chemicals[i].name);
 }
}

void destroy_semaphores()
{
	// *** HIER EUER CODE ***
  for (int i = 0; i < CHEMICAL_NUM; i++) 
    { 
      if(sem_destroy(&chemicals[i].sem)!=0)
	{
	  perror("sem_destroy");
	  exit(EXIT_FAILURE);
	}
    }
}
