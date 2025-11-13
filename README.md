📜 Implementación de Apache Karaf con Bundles Inter-relacionados y Desacoplados

Este proyecto es una implementación práctica de Apache Karaf utilizando el estándar OSGi (Open Services Gateway initiative) para crear un sistema altamente modular. El objetivo es demostrar cómo dos componentes de software (bundles) pueden estar inter-relacionados (uno provee un servicio y el otro lo consume) pero manteniendo un bajo acoplamiento, lo que permite una gestión dinámica de los componentes en tiempo de ejecución.

El desacoplamiento garantiza que se pueda arrancar y parar un bundle a demanda, sin afectar la ejecución del sistema ni requerir el reinicio del contenedor Karaf.

🔗 Enlace al Repositorio
Puedes encontrar el código fuente completo de este proyecto en GitHub: https://github.com/DavidPuga04/OSGI.git

📋 Prerrequisitos
Para clonar, construir y ejecutar este proyecto, debes tener instalados los siguientes programas:

1. Java Development Kit (JDK) 22
Este proyecto está compilado y probado específicamente con JDK 22. Es fundamental usar esta versión o una superior compatible.

Verificación: Abre tu terminal y ejecuta java -version.

Instalación: Si no lo tienes, puedes descargarlo de la página oficial de Oracle.

2. Apache Maven
Maven es la herramienta de construcción de proyectos que utilizamos para compilar el código fuente y empaquetar los bundles.

Verificación: Abre tu terminal y ejecuta mvn -v.

Instalación: Descárgalo e instálalo desde la página oficial de Apache Maven.

3. Apache Karaf
Es el contenedor de ejecución donde desplegaremos nuestros bundles.

Descarga e Instalación:

Descarga la versión estable más reciente de Apache Karaf desde su página oficial de descargas.

Descomprime el archivo en una ubicación fácil de recordar (ej: C:\karaf o ~/karaf). Llamaremos a esta carpeta [KARAF_HOME].

4. Entorno de Desarrollo (IDE)
Se utilizó IntelliJ IDEA Community Edition para el desarrollo y es el IDE recomendado para explorar y modificar el código.

⚙️ Pasos para la Ejecución

Sigue estos pasos para poner en marcha el sistema:

Paso 1: Clonar el Repositorio

Abre IntelliJ IDEA y clona este proyecto de GitHub:

https://github.com/DavidPuga04/OSGI.git

Paso 2: Construir los Bundles con Maven

Ejecuta el siguiente comando en la carpeta raíz del proyecto (OSGI/). Este comando compila todo el código y empaqueta los dos bundles funcionales:

Bash

mvn clean install

Una vez finalizado, los archivos .jar de los bundles listos para ser desplegados se encontrarán dentro de las carpetas target/ de cada módulo (modulo-productor/target/ y modulo-consumidor/target/).

Paso 3: Iniciar Apache Karaf

Navega a la carpeta bin dentro de tu instalación de Karaf ([KARAF_HOME]/bin) y ejecuta el script de inicio:

En Windows:

Bash

cd [KARAF_HOME]\bin

karaf.bat

La consola de Karaf se iniciará y verás un prompt similar a karaf@root()>.

Paso 4: Instalar y Arrancar los Bundles

Dentro de la consola de Karaf, instala los dos bundles funcionales. Es fundamental usar la ruta completa de los archivos JAR generados en el Paso 2.

Ejecuta estos comandos en la consola de Karaf (reemplaza la ruta local por la tuya):

Instalar Bundle Productor:

Bash

install file:/[RUTA_COMPLETA_AL_PROYECTO]/OSGI/modulo-productor/target/modulo-productor-1.0-SNAPSHOT.jar

Instalar Bundle Consumidor:

Bash

install file:/[RUTA_COMPLETA_AL_PROYECTO]/OSGI/modulo-consumidor/target/modulo-consumidor-1.0-SNAPSHOT.jar

Verificar IDs e Iniciar: Usa el comando bundle:list para ver los IDs de los bundles recién instalados (deberían estar en estado Installed):

Bash

bundle:list

Identifica los IDs (ej: 80 y 81) y arranca ambos:

Bash

# Arrancar el bundle Productor (ej. ID 80)

bundle:start <ID_Productor>

# Arrancar el bundle Consumidor (ej. ID 81)

bundle:start <ID_Consumidor>

Si todo es correcto, el Bundle Consumidor empezará a imprimir mensajes en la consola, utilizando el servicio del Bundle Productor.

✅ Demostración de Desacoplamiento (Hot-Swap)

La prueba clave del proyecto es demostrar que puedes detener un componente sin afectar la ejecución del resto del sistema Karaf.

1. Detener el Bundle Consumidor
   
Detén el Bundle Consumidor usando su ID, sin tocar el Bundle Productor:

Bash

# Detener el bundle Consumidor (ej. ID 81)

bundle:stop <ID_Consumidor>

Resultado Esperado:

Los mensajes periódicos del Consumidor dejarán de aparecer.

Si usas bundle:list, el Bundle Productor seguirá en estado Active. Esto demuestra que su ciclo de vida es independiente y no fue afectado por la detención del componente que consume su servicio.

2. Arrancar el Bundle Consumidor
   
Vuelve a arrancar el Bundle Consumidor para restaurar la funcionalidad del sistema.

Bash

# Arrancar el bundle Consumidor (ej. ID 81)

bundle:start <ID_Consumidor>

Resultado Esperado:

El Bundle Consumidor regresará al estado Active.

Los mensajes periódicos se reanudarán, demostrando la capacidad de gestión de componentes en caliente (hot-swapping) que ofrece OSGi a través de Apache Karaf.
