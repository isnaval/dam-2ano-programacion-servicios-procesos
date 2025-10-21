#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    pid_t pid;
    int variable = 6;
    int status;

    printf("Valor inicial de la variable: %d\n", variable);

    pid = fork();

    if (pid == 0)
    {
        variable = variable - 5;
        printf("Soy el hijo. Valor varible es de %d\n", variable);
    }
    else
    {
        variable = variable + 5;
        printf("Soy el padre. Valor variable es de %d\n", variable);
        wait(&status);
    }

    return 0;
}
