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
	
	for (size_t i = address; i <= range; i++) 
	{
		if (i < range) 
		{
			allocation_list[i].status = CHUNK_FREE; 
		}
		else if (i == range && allocation_list[range].status == CHUNK_FREE) 
		{
			to_attach = allocation_list[range].length; 
			allocation_list[address].length = to_free+to_attach;
			allocation_list[range].length = 0;
		}  
	}
	
	while (j >= zero) 
	{
		if (allocation_list[j].status == CHUNK_FREE)
		{
			if (j > zero && allocation_list[j].length != 0)
			{
				j--; 
			}
			else if (allocation_list[j].length > 0)
			{
				to_attach = allocation_list[j].length; 
				allocation_list[j].length = allocation_list[address].length+to_attach; 
				allocation_list[address].length = 0; 
				break;  
			} 
		}  
	 
	}  
	
	
	
}
