#ifndef __VORGABE_H__
#define __VORGABE_H__

#include <semaphore.h>

#define CHEMICAL_NUM 5		// Anzahl der verschiedenen Chemikalien.
#define CHEMIST_NUM 6		// Anzahl der Chemiker.
#define RECIPES_PER_CHEMIST 2	// Anzahl der Rezepte, die ein Chemiker mischt, bevor er sich beendet.
#define RECIPE_SIZE 4		// Anzahl der Zutaten eines Rezeptes.

typedef struct chemical {	// Definiere Struktur für Chemikalien mit "chemical_t" als Alias.
	char name[256];		// Der Name dieser Chemikalie.
	int time_needed;	// Die Zeit in Sekunden, die zur Verarbeitung dieser Chemikalie benötigt wird.
	int bottles;		// Die anfängliche Anzahl der vorhandenen Flaschen dieser Chemikalie.
	sem_t sem;		// Die noch verfügbare Flaschenzahl als Semaphore.
} chemical_t;

// Vorgegebene Chemikalien
chemical_t chemicals[CHEMICAL_NUM];

void get_recipe(chemical_t * recipe_buffer[]);

pthread_mutex_t lock;

int restart;

pthread_t chemists[CHEMIST_NUM];

// a3_a.c

void init_semaphores();
void destroy_semaphores();

// a3_b.c / a3_c.c / a3_d.c

void *work(void *arg);

#endif
