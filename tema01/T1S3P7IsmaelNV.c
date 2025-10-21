#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()

{

    pid_t pid1, pid2, pid3;
    pid1 = fork();

    if (pid1 == -1)
    {
        printf("Error al crear el hijo 1\n");
        exit(-1);
    };

    if (pid1 == 0)
    {
        printf("Yo soy el hijo 1, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());
        exit(0);
    }

    pid2 = fork();

    if (pid2 == -1)
    {
        printf("Error al crear el hijo 2\n");
        exit(-1);
    };

    if (pid2 == 0)
    {
        printf("Yo soy el hijo 2, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());
        exit(0);
    }

    return 0;

    pid3 = fork();

    if (pid3 == -1)
    {
        printf("Error al crear el hijo 3\n");
        exit(-1);
    };

    if (pid3 == 0)
    {
        printf("Yo soy el hijo 3, mi padre es PID=%d, yo soy PID=%d\n", getppid(), getpid());
        exit(0);
    }

    wait(NULL);
    wait(NULL);
    wait(NULL);

    printf("\nSoy el padre con PID=%d. Todos mis hijos han acabado sus procesos.\n", getpid());

    return 0;
}
