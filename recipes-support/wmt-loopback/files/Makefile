ifneq ($(CROSS_COMPILE),)
CROSS-COMPILE:=$(CROSS_COMPILE)
endif
CC:=$(CROSS-COMPILE)gcc
LD:=$(CROSS-COMPILE)ld
CFLAGS=-c -Wall -s
LDFLAGS=
LIBS=
SOURCES=wmt_loopback.c
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=wmt_loopback

.PHONY: all clean

all: $(SOURCES) $(EXECUTABLE)
    
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@ $(LIBS)

.c.o:
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(OBJECTS) $(EXECUTABLE)