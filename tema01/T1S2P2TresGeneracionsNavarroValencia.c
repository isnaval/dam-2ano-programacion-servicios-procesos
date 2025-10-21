#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    pid_t pid_padre;
    pid_t pid_nieto;
    int status_padre;
    int status_nieto;

    pid_padre = fork();

    if (pid_padre == 0)
    {
        pid_nieto = fork();

        if (pid_nieto == 0)
        {
            printf("Soy el nieto (%d hijo de %d): ", getpid(), getppid());
        }
        else
        {
            waitpid(pid_nieto, &status_nieto, 0);
            printf("Soy el padre (%d hijo de %d)", getpid(), getppid());
        }
    }
    else
    {
        waitpid(pid_padre, &status_padre, 0);
        printf("Soy el abuelo (%d hijo de %d)", getpid(), getppid());
    }
    return 0;
}