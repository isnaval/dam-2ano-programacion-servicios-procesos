/**
 * EJERCICIO 3: PIPE BÁSICO (Padre → Hijo)
 * Objetivo: Comunicación entre procesos mediante pipe
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

int main()
{
    printf("=== EJERCICIO 3: PIPE BÁSICO ===\n\n");

    int fd[2];
    pipe(fd);

    int pid = fork();

    if (pid == 0)
    {
        close(fd[1]);

        char buffer[100];
        read(fd[0], buffer, sizeof(buffer));

        printf("HIJO recibió: %s\n", buffer);
        close(fd[0]);
    }
    else
    {
        close(fd[0]);

        char mensaje[] = "Hola desde el padre!";
        write(fd[1], mensaje, strlen(mensaje));

        printf("PADRE envió: %s\n", mensaje);
        close(fd[1]);

        wait(NULL);
    }

    return 0;
}