# 📌 CRUD de Empleados en Java

Este proyecto implementa un sistema CRUD básico (Crear, Leer, Actualizar y Eliminar) para gestionar empleados utilizando archivos de texto plano en Java.

---

## 🖥️ Funcionalidades

### 1. Menú en consola
El programa muestra un menú interactivo con las siguientes opciones:

#### (1) Agregar empleado
- Solicita: **ID, Nombre, Cargo, Salario**.
- Valida que el **ID no exista** previamente en el archivo antes de agregarlo.

#### (2) Listar empleados
- Muestra todos los empleados en formato de tabla:
   ID | Nombre | Cargo | Salario

#### (3) Actualizar salario
- Solicita el **ID del empleado** y el **nuevo salario**.
- Si el ID existe, se actualiza el registro en el archivo.
- Si no existe, muestra un mensaje de error.

#### (4) Eliminar empleado
- Solicita el **ID del empleado**.
- Si existe, se elimina del archivo.
- Si no existe, muestra un mensaje de error.

#### (5) Salir
- Termina la ejecución del programa.

---

## 📂 Manejo de archivos
- Si el archivo `empleados.txt` **no existe**, se creará automáticamente al agregar el primer empleado.
- El archivo se **actualiza inmediatamente** cada vez que se realiza un cambio:
  - Agregar
  - Actualizar
  - Eliminar

---

## ⚠️ Manejo de errores
- Se valida que las entradas **no estén vacías**.
- Se valida que los datos ingresados tengan el **formato correcto**:
  - `ID` debe ser numérico.
  - `Salario` debe ser numérico.
  - El resto de campos deben tener información válida.

---


