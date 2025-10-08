#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>

#define FIFO_FILE "mi_fifo"
#define BUFFER_SIZE 100

int main(void)
{
    int fp;
    char buffer[BUFFER_SIZE];

    if (access(FIFO_FILE, F_OK) == -1)
    {
        if (mkfifo(FIFO_FILE, 0666) == -1)
        {
            perror("Error al crear FIFO");
            exit(1);
        }
        else
        {
            printf("FIFO creado correctamente.\n");
        }
    }
    else
    {
        printf("FIFO ya existe.\n");
    }

    printf("Consumidor: FIFO creado, Esperando datos ...\n");

    // PASO 1
    fp = open(FIFO_FILE, O_RDONLY);
    if (fp == -1)
    {
        printf("Error al abrir el FIFO\n");
        exit(1);
    }

    while (1)
    {
        ssize_t bytes = read(fp, buffer, BUFFER_SIZE - 1);
        if (bytes <= 0)
        {
            break;
        }

        buffer[bytes] = '\0';
        printf("Consumidor recibió: %s\n", buffer);

        if (buffer[0] == '.')
        {
            printf("Consumidor: Señal de fin recibida.\n");
            break;
        }
    }

    close(fp);
    unlink(FIFO_FILE);

    printf("Consumidor: Terminado correctamente.\n");
    return 0;
}
