ifneq ($(CROSS_COMPILE),)
CROSS-COMPILE:=$(CROSS_COMPILE)
endif
CC:=$(CROSS-COMPILE)gcc
LD:=$(CROSS-COMPILE)ld
CFLAGS=-c -Wall -s
LDFLAGS=
LIBS=
SOURCES=wmt_loader.c
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=wmt_loader

.PHONY: all clean

all: $(SOURCES) $(EXECUTABLE)
    
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@ $(LIBS)

.c.o:
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(OBJECTS) $(EXECUTABLE)