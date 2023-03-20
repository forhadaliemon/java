class Calculator<T extends Number> implements Operation<T> {
    public T add(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) (Integer) ((Integer) num1 + (Integer) num2);
        } else if (num1 instanceof Double) {
            return (T) (Double) ((Double) num1 + (Double) num2);
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + num1.getClass().getSimpleName());
        }
    }

    public T subtract(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) (Integer) ((Integer) num1 - (Integer) num2);
        } else if (num1 instanceof Double) {
            return (T) (Double) ((Double) num1 - (Double) num2);
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + num1.getClass().getSimpleName());
        }
    }

    public T multiply(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) (Integer) ((Integer) num1 * (Integer) num2);
        } else if (num1 instanceof Double) {
            return (T) (Double) ((Double) num1 * (Double) num2);
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + num1.getClass().getSimpleName());
        }
    }

    public T divide(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) (Integer) ((Integer) num1 / (Integer) num2);
        } else if (num1 instanceof Double) {
            return (T) (Double) ((Double) num1 / (Double) num2);
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + num1.getClass().getSimpleName());
        }
    }
}