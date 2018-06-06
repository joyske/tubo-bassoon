#include <stdlib.h>
#include <stdio.h>
// Zusätzliche Header-Dateien hier!
#include <unistd.h>

#include "vorgabe.h"

void *work(void *arg)
{
	int my_num = *((int *)arg);
	int value; 
	
	printf("[Ch%d] Ich bin bereit zu arbeiten!\n", my_num);

	// *** HIER EUER CODE ***
	chemical_t *ingredients[RECIPE_SIZE];
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
		sem_wait(&(*ingredients[j]).sem);
		sem_getvalue(&(*ingredients[j]).sem, &value); 
		printf("[Ch%d] ich nehme mir die Chemikalie %s! Es sind jetzt noch %d Flaschen davon vorhanden!\n", my_num, (*ingredients[j]).name, value);

		//dann verarbeiten wir die Chemikalien nacheinander
	        sleep((*ingredients[j]).time_needed);
		printf("[Ch%d] %s wird von mir verarbeitet... \n", my_num, (*ingredients[j]).name);		
	      }

	    //erst nachdem wir ALLE in unserem Rezept benoetigten Chemikalien 
	    //verarbeitet haben
	    for (int r = 0; r < RECIPE_SIZE; r++) 
	      {
		//...werden die Chemikalienflaschen wieder zurueck gebracht 
		//sem wird freigegeben bzw. incrementiert 
		//da die Chemikalie ja wieder fuer andere Chemiker verfuegbar ist
		sem_post(&(*ingredients[r]).sem);
		printf("[Ch%d] %s wird jetzt von mir zurück gebracht! \n", my_num, (*ingredients[r]).name);
	      }
	  }
}
