1. Lo primero que hice fue una clase que extienda Thread. En el constructor le pongo el nombre "TIC" a la clase. Creo el método run() es donde pongo la lógica de que imprime "TIC "
   Espera 1 segundo y que si hay un error (InterruptedException) lo capturo y cierro el thread

2. Luego hago otra clase igual que la anterior pero para el TAC.

3. Luego hago la clase para recoger la función principal y aqui instancio a los dos objetos para las dos clases anteriores. Además utilizo el metodo start() para iniciar los dos objeetos y como estan secuenciados a un segundo se imprimira TAC TIC ...

4. Compilo en java las tres clases y ejecuto la principal

Autor: Navarro/Valencia
Asignatura: PSP - Programación Multifil
Centro: IES Abastos - 2n DAM
