#include <stdlib.h>
#include <stdio.h>
// Zusätzliche Header-Dateien hier!

#include "vorgabe.h"

chemical_t *ingredients[RECIPE_SIZE];
chemical_t chemical;

void *work(void *arg)
{
	int my_num = *((int *)arg);

	printf("[Ch%d] Ich bin bereit zu arbeiten!\n", my_num);

	// *** HIER EUER CODE ***
	for (int i = 0; i < RECIPES_PER_CHEMIST; i++)
	  { 
	    //Wir generieren uns ein Rezept mit 4 Zutaten 
	    get_recipe(ingredients);

	    //dann gehen wir die Zutatenliste durch
	    for (int j = 0; j < RECIPE_SIZE; j++)
	      {
		//...und holen uns unsere Zutaten eine nach der anderen
		//jedes Mal wenn eine Chemikalienflasche geholt wird 
		//muss sem (die Anzahl der verbleibenen Flaschen im Regal)
		//dekrementiert werden da ja eine Flasche weniger
		//zur Verfuegung steht 
		sem_wait(&chemical.sem);

		//dann verarbeiten wir die Chemikalien nacheinander
		sleep(&chemicals[j].time_needed);  
	      }

	    //erst nachdem wir ALLE in unserem Rezept benoetigten Chemikalien 
	    //verarbeitet haben
	    for (int r = 0; r < RECIPE_SIZE; r++) 
	      {
		//...werden die Chemikalienflaschen wieder zurueck gebracht 
		//sem wird freigegeben bzw. incrementiert 
		//da die Chemikalie ja wieder fuer andere Chemiker verfuegbar ist
		sem_post(&chemical.sem);
	      }
	  }
}
