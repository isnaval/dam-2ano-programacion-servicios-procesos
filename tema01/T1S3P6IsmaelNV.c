#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    pid_t pid;
    int i;

    pid = fork();

    switch (pid)
    {
    case -1:
        printf("Error al crear al proceso de hijo\n");
        exit(-1);

    case 0:
        for (i = 0; i < 10; i++)
        {
            printf("Soy el hijo\n");
            usleep(100000);
        }
        break;
    default:
        for (i = 0; i < 10; i++)
        {
            printf("Soy el padre\n");
            usleep(100000);
        }
        wait(NULL);
        printf("\n Mi proceso hijo ha acabado\n");
        break;
    }

    return 0;
}
