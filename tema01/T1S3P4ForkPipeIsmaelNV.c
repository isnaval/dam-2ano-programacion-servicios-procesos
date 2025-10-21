#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    // PASO 1
    int fd[2];
    char buffer[30];
    char mensaje[] = "Hola Ismael";
    pid_t pid;

    // PASO 2
    if (pipe(fd) == -1)
    {
        printf("Error al crear el pipe\n");
        exit(-1);
    }

    // PASO 3
    pid = fork();
    switch (pid)
    {
    case -1:
        printf("No se ha podido crear el hijo\n");
        exit(-1);
    case 0:
        close(fd[0]);
        printf("El hijo envia al padre el mensaje ...\n");
        write(fd[1], mensaje, strlen(mensaje) + 1);
        close(fd[1]);
        break;
    default:
        close(fd[1]);
        wait(NULL);
        read(fd[0], buffer, sizeof(buffer));
        printf("El padre recibe del hijo: %s\n", buffer);
        close(fd[0]);
        break;
    }

    return 0;
}
