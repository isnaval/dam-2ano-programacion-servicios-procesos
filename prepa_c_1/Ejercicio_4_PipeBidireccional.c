/**
 * EJERCICIO 4: PIPE BIDIRECCIONAL (Padre ↔ Hijo)
 * Objetivo: Comunicación en ambas direcciones
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

int main()
{
    printf("=== EJERCICIO 4: PIPE BIDIRECCIONAL ===\n\n");

    int fd1[2];
    int fd2[2];

    pipe(fd1);
    pipe(fd2);

    int pid = fork();

    if (pid == 0)
    {
        close(fd1[1]);
        close(fd2[0]);

        char buffer[100];
        read(fd1[0], buffer, sizeof(buffer));
        printf("HIJO recibió: %s\n", buffer);

        char respuesta[] = "Respuesta desde el hijo!";
        write(fd2[1], respuesta, strlen(respuesta));
        printf("HIJO envió: %s\n\n", respuesta);

        close(fd1[0]);
        close(fd2[1]);
    }
    else
    {
        close(fd1[0]);
        close(fd2[1]);

        char mensaje[] = "Mensaje desde el padre!";
        write(fd1[1], mensaje, strlen(mensaje));
        printf("PADRE envió: %s\n", mensaje);

        char buffer[100];
        read(fd2[0], buffer, sizeof(buffer));
        printf("PADRE recibió: %s\n", buffer);

        close(fd1[1]);
        close(fd2[0]);

        wait(NULL);
    }

    return 0;
}