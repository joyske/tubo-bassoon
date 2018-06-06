#include <time.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "vorgabe.h"

chemical_t chemicals[CHEMICAL_NUM] =
    { {"Schwefel", 3, 3}, {"Phosphor", 2, 2}, {"Salzsauere", 2, 2},
    {"Wasserstoffperoxid", 4, 2}, {"Aceton", 1, 4} };

// Funktion, die ein zufälliges Rezept generiert und im als Argument
// angegebenen (d.h. von euch mit der richtigen Länge anzulegenden!)
// Array speichert
void get_recipe(chemical_t * recipe_buffer[])
{
	chemical_t *chemicals_left[CHEMICAL_NUM];
	for (int i = 0; i < CHEMICAL_NUM; i++) {
		chemicals_left[i] = &(chemicals[i]);
	}

	for (int i = 0; i < RECIPE_SIZE; i++) {
		int rand_index = rand() % (CHEMICAL_NUM - i);
		recipe_buffer[i] = chemicals_left[rand_index];
		for (int j = rand_index; j < CHEMICAL_NUM - i - 1; j++) {
			chemicals_left[j] = chemicals_left[j + 1];
		}
	}
}

int main()
{
	srand(time(NULL));	// Zufallszahlengenerator initialisieren

	restart = 1;

	while (restart) {
		restart = 0;

		pthread_mutex_init(&lock, NULL);

		init_semaphores();
		printf("[ ! ] Semaphoren initialisiert.\n");

		// Nummernvariablen zur Übergabe an die Threads anlegen
		int chemist_nums[CHEMIST_NUM];
		for (int i = 0; i < CHEMIST_NUM; i++) {
			chemist_nums[i] = i;
		}

		// Chemiker-Threads anlegen
		for (int i = 0; i < CHEMIST_NUM; i++) {
			if (pthread_create
			    (&chemists[i], NULL, &work, &(chemist_nums[i]))) {
				perror("pthread_create");
				exit(1);
			}
		}

		// Chemiker-Threads einsammeln
		for (int i = 0; i < CHEMIST_NUM; i++) {
			if (pthread_join(chemists[i], NULL)) {
				perror("pthread_join");
				exit(1);
			}
		}

		printf("[ ! ] Alle Chemiker beendet.\n");

		if (pthread_mutex_destroy(&lock)) {
			perror("pthread_mutex_destroy");
			exit(1);
		}

		destroy_semaphores();

		printf("[ ! ] Semaphoren zerstört.\n");
	}

	printf("[!!!] Beende Programm.\n");

	return 0;
}
