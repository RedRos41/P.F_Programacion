Proyecto final - Programación 1

Integrantes:
Derek Rodriguez Rodriguez,
Jose Luis Carvajal,
Sara Vergara Quevedo.

### Documentación del Código
Este código define una aplicación JavaFX para gestionar un sistema de parqueadero. La aplicación permite agregar vehículos, verificar y eliminar puestos, calcular tarifas de estacionamiento y mostrar reportes diarios y mensuales. El objetivo principal del código es proporcionar una interfaz gráfica para gestionar el parqueadero y calcular tarifas basadas en el tipo de vehículo y el tiempo de permanencia.

#### Estructura del Código

1. *Clase Principal (Main)*
    - Extiende la clase Application de JavaFX.
    - Define los componentes de la interfaz de usuario y maneja eventos.
    - Contiene métodos auxiliares para mostrar alertas y actualizar el estado del parqueadero.

2. *Método main*
    - Inicializa las tarifas del parqueadero usando el método estático setTarifas de la clase Administrador.
    - Llama al método launch para iniciar la aplicación JavaFX.

3. *Método start*
    - Configura la ventana principal (primaryStage), estableciendo el título y la escena.
    - Crea y configura los elementos de la interfaz de usuario (textos, botones, campos de entrada, etc.).
    - Define acciones para los botones y otros componentes interactivos.


4. Clase *Parqueadero*


5. *Atributos Privados:*
    - Vehiculo[][] parqueadero: Matriz de vehículos que representa el parqueadero.

6. *Constructor:*
    - Parqueadero(): Constructor para inicializar el parqueadero.

7. *Métodos Públicos:*
    - Vehiculo crearVehiculo(String tipoVehiculo, String placa, int modelo, String propietario, int velocidadMaxima): Método para crear un vehículo.
    - int[] agregarVehiculo(Vehiculo vehiculo): Método para agregar un vehículo al parqueadero.
    - boolean verificarPuestoDisponible(): Método para verificar si hay algún puesto disponible en el parqueadero.
    - boolean verificarPuestoOcupado(int fila, int columna): Método para verificar si un puesto específico está ocupado.
    - boolean eliminarVehiculo(int fila, int columna): Método para eliminar un vehículo de un puesto específico.
    - Vehiculo[][] obtenerParqueadero(): Método para obtener la matriz del parqueadero.


8. Clase Administrador


9. Variables Estáticas:

TARIFA_MOTO_CLASICA: Tarifa para motos clásicas.
TARIFA_MOTO_HIBRIDA: Tarifa para motos híbridas.
TARIFA_CARRO: Tarifa para carros.
ingresoMotoClasica: Ingresos acumulados de motos clásicas.
ingresoMotoHibrida: Ingresos acumulados de motos híbridas.
ingresoCarro: Ingresos acumulados de carros.
ingresosDiarios: Mapa para registrar ingresos diarios.
ingresosMensuales: Mapa para registrar ingresos mensuales.

   10. Constructor:

Administrador(): Constructor de la clase, no realiza acciones explícitas en el código proporcionado.


11. Métodos Públicos:

static void setTarifas(int motoClasica, int motoHibrida, int carro): Establece las tarifas para cada tipo de vehículo.
static void IngresoMotoClasica(int ingreso): Registra el ingreso de una moto clásica.
static void IngresoMotoHibrida(int ingreso): Registra el ingreso de una moto híbrida.
static void IngresoCarro(int ingreso): Registra el ingreso de un carro.
static int getIngresoMotoClasica(): Devuelve el ingreso acumulado de motos clásicas.
static int getIngresoMotoHibrida(): Devuelve el ingreso acumulado de motos híbridas.
static int getIngresoCarro(): Devuelve el ingreso acumulado de carros.
static int[] getIngresosDiarios(java.time.LocalDate fecha): Devuelve los ingresos diarios para una fecha específica.
static int[] getIngresosMensuales(java.lang.String mes): Devuelve los ingresos mensuales para un mes específico.


12. Clase *ReportesParqueadero*


13. *Atributos Privados:*
   - List<ReportesParqueadero.Registro> registros: Lista de registros de vehículos en el parqueadero.

14. *Constructor:*
   - ReportesParqueadero(): Constructor para inicializar la lista de registros.

15. *Métodos Públicos:*
   - void agregarRegistro(int fila, int columna, Vehiculo vehiculo): Método para agregar un registro de vehículo al parqueadero.
   - List<ReportesParqueadero.Registro> obtenerRegistros(): Método para obtener la lista de registros de vehículos en el parqueadero.

16. Clase *ReportesTarifa*


17. *Constructor:*
   - ReportesTarifa(): Constructor por defecto.

18. *Métodos Estáticos:*
   - void mostrarReporteRecaudado(): Método estático para mostrar el reporte de la recaudación total.
   - String mostrarReporteDiario(LocalDate fecha): Método estático para mostrar el reporte diario de recaudación basado en una fecha especificada.
   - String mostrarReporteMensual(String mes): Método estático para mostrar el reporte mensual de recaudación basado en un mes especificado.


#### Componentes de la Interfaz

1. Formulario de Agregar Vehículo
    - ChoiceBox<String> tipoVehiculoChoiceBox: Selección del tipo de vehículo.
    - TextField placaField, modeloField, propietarioField, velocidadMaximaField: Campos de entrada para los datos del vehículo.
    - Button addButton: Botón para agregar el vehículo.
    - Acciones:
        - Validación de entrada (placa, modelo, propietario, velocidad máxima).
        - Creación del vehículo y adición al parqueadero.
        - Actualización del estado del parqueadero.

2. Formulario de Verificación y Eliminación de Puestos
    - TextField filaField, columnaField: Campos de entrada para la fila y columna.
    - Button checkButton: Botón para verificar si un puesto está ocupado.
    - TextField filaEliminarField, columnaEliminarField: Campos de entrada para eliminar un vehículo.
    - Button removeButton: Botón para eliminar el vehículo de un puesto específico.
    - Acciones:
        - Validación de entrada (fila, columna).
        - Verificación y eliminación del vehículo en la posición especificada.

3. Formulario de Cálculo de Tarifas
    - ComboBox<String> tipoVehiculoComboBoxTarifa: Selección del tipo de vehículo para calcular la tarifa.
    - TextField horasInput: Campo de entrada para el número de horas.
    - Button calcularButton: Botón para calcular la tarifa.
    - Label resultadoLabel: Etiqueta para mostrar el resultado del cálculo.
    - Acciones:
        - Validación de entrada (horas).
        - Cálculo del costo basado en el tipo de vehículo y las horas.

4. Botones de Reportes
    - Button reporteDiarioButton: Botón para mostrar el reporte diario.
    - Button reporteMensualButton: Botón para mostrar el reporte mensual.
    - Acciones:
        - Generación y visualización de reportes diarios y mensuales basados en la fecha actual.
