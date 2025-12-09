// #include <sys/types.h>
// #include <unistd.h>
// #include <stdio.h>
// #include <sys/wait.h>

// int main(int argc, char *argv[])
// {
//     pid_t pid_padre;
//     pid_t pid_nieto;
//     pid_t pid_bisniet;
//     int status_padre;
//     int status_nieto;
//     int status_bisnieto;

//     pid_padre = fork();

//     if (pid_padre == 0)
//     {
//         pid_nieto = fork();

//         if (pid_nieto == 0)
//         {
//             for (int i = 0; i < 3; i++)
//             {
//                 printf("Soy el bisnieto (%d bisnieto de %d)", getpid(), getppid());
//             }

//             printf("Soy el nieto (%d hijo de %d): ", getpid(), getppid());
//         }
//         else
//         {
//             waitpid(pid_nieto, &status_nieto, 0);
//             printf("Soy el padre (%d hijo de %d)", getpid(), getppid());
//         }
//     }
//     else
//     {
//         waitpid(pid_padre, &status_padre, 0);
//         printf("Soy el padre (%d hijo de %d)", getpid(), getppid());
//     }
//     return 0;
// }

#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    /**
     * declaro tres variables para hijo nieto y satus
     */

    pid_t pid_hijo;
    pid_t pid_nieto;
    int status;

    /**
     * creo primera iteracion - donde se inicia el proceso y el padre crea tres hijos pero solo el terquero hijo crea un nieto
     */
    for (int i = 0; i < 3; i++)
    {
        pid_hijo = fork();

        if (pid_hijo == 0)
        {
            if (i == 2) // en caso que el tercer hijo se inicie genera un niego
            {
                pid_nieto = fork();
                if (pid_nieto == 0)
                {

                    printf("Soy el nieto (PID: %d, hijo de %d)\n", getpid(), getppid());
                    return 0;
                }

                wait(&status);
            }

            printf("Soy el hijo %d (PID: %d, hijo de %d)\n", i + 1, getpid(), getppid());
            return 0;
        }
    }

    for (int i = 0; i < 3; i++)
    {
        wait(&status);
    }

    printf("Soy el padre (PID: %d), terminé de esperar a mis 3 hijos\n", getpid());
    return 0;
}