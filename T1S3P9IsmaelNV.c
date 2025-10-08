#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    pid_t pid1, pid2;
    int orden;

    printf("Introduce el orden de ejecucion:\n");
    printf("1 - Proceso 1 primero, luego Proceso 2\n");
    printf("2 - Proceso 2 primero, luego Proceso 1\n");
    printf("Introduce> ");
    scanf("%d", &orden);

    if (orden != 1 && orden != 2)
    {
        printf("Opcion invalida. Debe ser 1 o 2.\n");
        exit(-1);
    }

    pid1 = fork();
    if (pid1 == -1)
    {
        printf("Error al crear proceso 1\n");
        exit(-1);
    }

    if (pid1 == 0)
    {
        if (orden == 2)
        {
            sleep(1);
        }

        printf("Soy proceso 1 y termino\n");
        exit(0);
    }

    pid2 = fork();
    if (pid2 == -1)
    {
        printf("Error al crear proceso 2\n");
        exit(-1);
    }

    if (pid2 == 0)
    {
        if (orden == 1)
        {
            sleep(1);
        }

        printf("Soy proceso 2 y termino\n");
        exit(0);
    }

    wait(NULL);
    wait(NULL);

    return 0;
}
