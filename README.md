# 🛍️ ComprasApp

Una aplicación Android que permite explorar productos, escanear códigos QR y simular compras según el país seleccionado. Implementada con arquitectura MVVM, Retrofit, Hilt, Navigation Component y soporte para dos APIs: FakeStore y Platzi Fake Store.

---

## 📲 Funcionalidades principales

- 🔐 **Inicio de sesión simple** (hardcodeado para el challenge).
- 🌍 **Selección de país**: FakeStore o Platzi Fake Store.
- 🛒 **Catálogo de productos** filtrado por país.
- 📄 **Detalle del producto** con título, descripción, imagen y precio.
- 💳 **Simulación de compra** con formulario de tarjeta.
- 📷 **Escaneo de QR** para acceder al detalle del producto por su ID.
- ✅ **Validaciones** para tarjeta correcta y errores en escaneo QR.

---

## 🧰 Tecnologías utilizadas

- Kotlin + Android Jetpack
- MVVM Architecture
- Hilt (DI)
- Retrofit (API REST)
- Navigation Component
- ZXing para escaneo QR
- LiveData + ViewModel
- Material Components

---

## 🚀 Cómo ejecutar la app

### 🔧 Requisitos previos

- Android Studio Iguana o más reciente
- SDK mínimo 24+
- Conexión a internet para consumir las APIs

### ▶️ Pasos para compilar y ejecutar

1. Cloná este repositorio:

```bash
git clone https://github.com/NavarroFederico/ComprasApp.git
```

2. Abrí el proyecto en **Android Studio**.

3. Esperá a que se sincronicen las dependencias (Gradle).

4. Ejecutá la app en un emulador o dispositivo físico con Android 7.0+.

---

🔐 Credenciales de acceso
Para iniciar sesión en la aplicación, usá el siguiente usuario:

makefile
Copy
Edit
Usuario: usuario
Contraseña: 1234
💳 Datos de prueba para el pago
Usá los siguientes datos en la pantalla de pago:

Número de tarjeta: 4242 4242 4242 4242

CVV: 123

Fecha de expiración: 12/25

Cualquier otro número de tarjeta generará un error de pago.

📱 Funcionalidad de escaneo QR
La app permite escanear un código QR para obtener un producto y acceder directamente al detalle.
Para probar esta función rápidamente, podés escanear uno de los siguientes códigos QR desde tu pantalla:

QRs de prueba
Producto 5





![qr_product_5](https://github.com/user-attachments/assets/e0000386-6333-4c70-8bca-f09630fa48e8)

Producto 8





![qr_product_8](https://github.com/user-attachments/assets/d2c2d8ec-4d94-4368-a3f5-ab8573819b43)

📝 Cada código QR representa el ID de un producto válido en la API seleccionada.

## 📲 Algunos puntos para mejorar a futuro

- Rediseño y personalización de la UI

- Si el producto no existe en la petición que diga producto no encontrado

- Login con credeciales de Google

- Testing

## 🙌 Autor

Desarrollado por Navarro Federico como parte de un challenge técnico para IT ROCK.

Si te gustó el proyecto o tenés feedback, ¡no dudes en escribirme!
navarrofedericodev@gmail.com

