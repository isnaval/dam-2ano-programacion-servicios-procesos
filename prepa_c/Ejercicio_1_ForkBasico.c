/**
 * EJERCICIO 1: FORK BÁSICO
 * Objetivo: Entender cómo funciona fork()
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    printf("=== EJERCICIO 1: FORK BÁSICO ===\n\n");

    printf("PID del proceso actual: %d\n", getpid());
    printf("PPID (padre): %d\n\n", getppid());

    int pid = fork();

    if (pid == 0)
    {
        printf("SOY EL HIJO\n");
        printf("  - Mi PID: %d\n", getpid());
        printf("  - PID de mi padre: %d\n\n", getppid());
    }
    else if (pid > 0)
    {
        printf("SOY EL PADRE\n");
        printf("  - Mi PID: %d\n", getpid());
        printf("  - PID del hijo: %d\n\n", pid);

        wait(NULL);
        printf("El hijo ha terminado\n");
    }
    else
    {
        printf("ERROR en fork()\n");
    }

    return 0;
}