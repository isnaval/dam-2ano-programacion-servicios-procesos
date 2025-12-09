/**
 * EJERCICIO 6: SEMÁFOROS BÁSICOS
 * Objetivo: Proteger sección crítica con semáforo
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/sem.h>
#include <sys/ipc.h>

int main()
{
    printf("=== EJERCICIO 6: SEMÁFOROS BÁSICOS ===\n\n");

    int semid = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);

    int pid = fork();

    if (pid == 0)
    {
        struct sembuf op;
        op.sem_num = 0;
        op.sem_op = -1;
        op.sem_flg = 0;

        semop(semid, &op, 1);

        printf("HIJO entra en sección crítica\n");
        printf("HIJO está trabajando...\n");
        sleep(2);
        printf("HIJO sale de sección crítica\n");

        op.sem_op = 1;
        semop(semid, &op, 1);
    }
    else
    {
        sleep(1);

        struct sembuf op;
        op.sem_num = 0;
        op.sem_op = -1;
        op.sem_flg = 0;

        printf("PADRE intenta entrar...\n");
        semop(semid, &op, 1);

        printf("PADRE entra en sección crítica\n");
        printf("PADRE está trabajando...\n");
        sleep(1);
        printf("PADRE sale de sección crítica\n");

        op.sem_op = 1;
        semop(semid, &op, 1);

        wait(NULL);
    }

    return 0;
}