/**
 * EJERCICIO 5: SEÑALES BÁSICAS
 * Objetivo: Manejo de señales (SIGTERM, SIGINT)
 */

#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void handler(int sig)
{
    if (sig == SIGTERM)
    {
        printf("¡Recibí SIGTERM!\n");
    }
    else if (sig == SIGINT)
    {
        printf("¡Recibí SIGINT (Ctrl+C)!\n");
    }
}

int main()
{
    printf("=== EJERCICIO 5: SEÑALES BÁSICAS ===\n\n");

    signal(SIGTERM, handler);
    signal(SIGINT, handler);

    printf("Proceso en ejecución (PID: %d)\n", getpid());
    printf("Presiona Ctrl+C para enviar SIGINT\n");
    printf("O ejecuta: kill -SIGTERM %d\n\n", getpid());

    printf("Enviando SIGTERM a mí mismo...\n");
    kill(getpid(), SIGTERM);

    printf("Continúo ejecutándome después de la señal\n\n");

    printf("Esperando 10 segundos...\n");
    for (int i = 0; i < 10; i++)
    {
        printf(".");
        fflush(stdout);
        sleep(1);
    }
    printf("\n\nPrograma finalizado\n");

    return 0;
}