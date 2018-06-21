#include "4b.h"
#include <stdio.h>

void nf_free(void *addr)
{
	size_t address = (size_t)addr; 
	size_t to_free = allocation_list[address].length; 
	size_t range = address+to_free; 
	size_t to_attach; 
	size_t j = address-1;
	size_t zero = 0; 
	
	//wenn Adresse = 0 wird nichts gemacht 
	if (addr != NULL) 
	{
		//wenn die Adresse gültig ist...
		if (to_free > zero && allocation_list[address].status == CHUNK_ALLOCATED && address > zero)
		{
			//wird die range (Bereich den die Startadresse beschreibt) freigesetzt
			for (size_t i = address; i <= range; i++) 
			{
				if (i < range) 
				{
					allocation_list[i].status = CHUNK_FREE; 
				}
				//danach wird geguckt ob der nächste Speicherblock noch frei ist
				else if (i == range && allocation_list[range].status == CHUNK_FREE) 
				{
					//ist er es, wird er mit unserem gerade freigesetzten Speicherblock 
					//zu einem großen Speicherblock zusammengesetzt 
					to_attach = allocation_list[range].length; 
					allocation_list[address].length = to_free+to_attach;
					allocation_list[range].length = zero;
				}  
			}
	
			while (j >= zero) 
			{
				//jezt überprüfen wir ob auch der vorherige Speicherblock frei ist
				if (allocation_list[j].status == CHUNK_FREE)
				{
					if (j > zero && allocation_list[j].length == zero)
					{
						j--; 
					}
					else if (allocation_list[j].length > zero)
					{
						//wenn ja, wird auch er zum großen freien Block hinzugefügt
						to_attach = allocation_list[j].length; 
						allocation_list[j].length = allocation_list[address].length+to_attach; 
						allocation_list[address].length = zero; 
						break;  
					} 
				}  
	 
			}
			
		}
		else 
		{
			printf("\n [!] FEHLER! Ungültige Adresse!");
			return 255;
		}
		
	} 
	
}
