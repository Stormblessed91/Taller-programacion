public class Vehiculo {
    private String marca;
    private int año;
    private double precio;
    private int capacidadEstanque;
    private int nivelEstanque;

    public Vehiculo(String marca, int año, double precio, int capacidadEstanque, int nivelEstanque) {
        this.marca = marca;
        this.año = año;
        this.precio = precio;
        this.capacidadEstanque = capacidadEstanque;
        this.nivelEstanque = nivelEstanque;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidadEstanque() {
        return capacidadEstanque;
    }

    public void setCapacidadEstanque(int capacidadEstanque) {
        this.capacidadEstanque = capacidadEstanque;
    }

    public int getNivelEstanque() {
        return nivelEstanque;
    }

    public void setNivelEstanque(int nivelEstanque) {
        this.nivelEstanque = nivelEstanque;
    }

    public void ImprimirDatos() {
        System.out.println("Marca: " + marca);
        System.out.println("Año: " + año);
        System.out.println("Precio: " + precio);
        System.out.println("Capacidad de Estanque: " + capacidadEstanque);
        System.out.println("Nivel de Estanque: " + nivelEstanque);
    }

    public void CargarEstanque(int litros) {
        if (litros < 0) {
            throw new IllegalArgumentException("La cantidad de litros debe ser positiva.");
        }
        if (nivelEstanque + litros > capacidadEstanque) {
            throw new IllegalArgumentException("No se puede cargar más combustible que la capacidad del estanque.");
        }
        nivelEstanque += litros;
    }

    public void VaciarEstanque(int litros) {
        if (litros < 0) {
            throw new IllegalArgumentException("La cantidad de litros debe ser positiva.");
        }
        if (nivelEstanque - litros < 0) {
            throw new IllegalArgumentException("El nivel de estanque no puede ser menor que 0.");
        }
        nivelEstanque -= litros;
    }

    public double calcularImpuesto() {
        return precio * 0.015;
    }
}

public class Automovil extends Vehiculo {
    private int cantidadPuertas;

    public Automovil(String marca, int año, double precio, int capacidadEstanque, int nivelEstanque, int cantidadPuertas) {
        super(marca, año, precio, capacidadEstanque, nivelEstanque);
        if (cantidadPuertas % 2 != 0) {
            throw new IllegalArgumentException("La cantidad de puertas no puede ser impar.");
        }
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        if (cantidadPuertas % 2 != 0) {
            throw new IllegalArgumentException("La cantidad de puertas no puede ser impar.");
        }
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public void CargarEstanque(int litros) {
        if (litros < 0) {
            throw new IllegalArgumentException("La cantidad de litros debe ser positiva.");
        }
        if (getNivelEstanque() + litros > getCapacidadEstanque()) {
            throw new IllegalArgumentException("No se puede cargar más combustible que la capacidad del estanque.");
        }
        super.CargarEstanque(litros);
    }

    @Override
    public void VaciarEstanque(int litros) {
        if (litros < 0) {
            throw new IllegalArgumentException("La cantidad de litros debe ser positiva.");
        }
        if (getNivelEstanque() - litros < 15) {
            throw new IllegalArgumentException("El nivel de estanque no puede ser menor que 15 litros.");
        }
        super.VaciarEstanque(litros);
    }

    @Override
    public double calcularImpuesto() {
        if (getAño() < 2010) {
            return 0.0;
        }
        return super.calcularImpuesto();
    }

    public void ModificarAño(int nuevoAño) {
        setAño(nuevoAño);
    }
}

public class Motocicleta extends Vehiculo {
    private int cilindrada;

    public Motocicleta(String marca, int año, double precio, int capacidadEstanque, int nivelEstanque, int cilindrada) {
        super(marca, año, precio, capacidadEstanque, nivelEstanque);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void VaciarEstanque(int litros) {
        if (litros < 0) {
            throw new IllegalArgumentException("La cantidad de litros debe ser positiva.");
        }
        if (getNivelEstanque() - litros < 5) {
            throw new IllegalArgumentException("El nivel de estanque no puede ser menor que 5 litros.");
        }
        super.VaciarEstanque(litros);
    }

    @Override
    public void ImprimirDatos() {
        super.ImprimirDatos();
        System.out.println("Cilindrada: " + cilindrada);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Automovil automovil = new Automovil("Toyota", 2015, 15000.0, 50, 30, 4);
            System.out.println("Automóvil:");
            automovil.ImprimirDatos();
            automovil.CargarEstanque(20);
            automovil.ImprimirDatos();
            automovil.VaciarEstanque(10);
            automovil.ImprimirDatos();
            System.out.println("Impuesto a pagar: $" + automovil.calcularImpuesto());
            automovil.ModificarAño(2020);
            automovil.ImprimirDatos();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            Motocicleta motocicleta = new Motocicleta("Honda", 2022, 8000.0, 20, 15, 250);
            System.out.println("\nMotocicleta:");
            motocicleta.ImprimirDatos();
            motocicleta.CargarEstanque(10);
            motocicleta.ImprimirDatos();
            motocicleta.VaciarEstanque(5);
            motocicleta.ImprimirDatos();
            System.out.println("Impuesto a pagar: $" + motocicleta.calcularImpuesto());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
