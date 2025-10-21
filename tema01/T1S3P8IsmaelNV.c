#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    pid_t pid1, pid2, pid_nieto;

    // 1 hijo
    pid1 = fork();
    if (pid1 == -1)
    {
        printf("Error al crear hijo 1\n");
        exit(-1);
    }

    if (pid1 == 0)
    {
        printf("Soy el hijo 1, mi padre es PID=%d, yo soy PID=%d\n",
               getppid(), getpid());
        exit(0);
    }

    // el hijo 1 se convierte en padre creando al nieto
    pid2 = fork();

    if (pid2 == -1)
    {
        printf("Error al crear hijo 2\n");
        exit(-1);
    }

    if (pid2 == 0)
    {
        printf("Soy el hijo 2, mi padre es PID=%d, yo soy PID=%d\n",
               getppid(), getpid());

        // aqui es donde creo aniadado el niego que es el hijo 1 del hijo 1
        pid_nieto = fork();

        if (pid_nieto == -1)
        {
            printf("Error al crear nieto\n");
            exit(-1);
        }

        if (pid_nieto == 0)
        {
            printf("Soy el nieto, mi padre es PID=%d, yo soy PID=%d\n",
                   getppid(), getpid());
            exit(0);
        }

        wait(NULL);
        printf("Hijo 2: Mi hijo (el nieto) ha terminado\n");
        exit(0);
    }

    wait(NULL);
    wait(NULL);

    printf("\nSoy el padre con PID=%d. Todos han terminado.\n", getpid());

    return 0;
}
