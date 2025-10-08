#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>

#define FIFO_FILE "mi_fifo"
#define BUFFER_SIZE 100

int main()
{
    int fp;
    char buffer[BUFFER_SIZE];

    printf("Productor iniciado. Escribe caracteres (punto '.' para terminar):\n");

    while (1)
    {
        printf("> ");
        fgets(buffer, BUFFER_SIZE, stdin);
        buffer[strcspn(buffer, "\n")] = 0;

        fp = open(FIFO_FILE, O_WRONLY);

        if (fp == -1)
        {
            printf("Error al abrir FIFO, el consumidor esta ejecutandose?\n");
            exit(1);
        }

        write(fp, buffer, strlen(buffer));
        close(fp);

        printf("Productor envio: %s\n", buffer);

        if (buffer[0] == '.')
        {
            printf("Productor: Terminado...\n");
            break;
        }
    }

    return 0;
}
