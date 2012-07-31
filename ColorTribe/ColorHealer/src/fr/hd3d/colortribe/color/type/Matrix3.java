package fr.hd3d.colortribe.color.type;

public class Matrix3 implements java.io.Externalizable {
    //      0,0   1,0   2,0
    //      0,1   1,1   2,1
    //      0,2   1,2   2,2
    public float matrix00;
    public float matrix01;
    public float matrix02;
    public float matrix10;
    public float matrix11;
    public float matrix12;
    public float matrix20;
    public float matrix21;
    public float matrix22;
    static final long serialVersionUID = -221342345998L;
    private static final int version = 100;

    public void writeExternal(java.io.ObjectOutput out) throws java.io.IOException {
        out.writeInt(version); // version track
        out.writeFloat(matrix00);
        out.writeFloat(matrix01);
        out.writeFloat(matrix02);
        out.writeFloat(matrix10);
        out.writeFloat(matrix11);
        out.writeFloat(matrix12);
        out.writeFloat(matrix20);
        out.writeFloat(matrix21);
        out.writeFloat(matrix22);
    }

    public void readExternal(java.io.ObjectInput in) throws java.io.IOException, ClassNotFoundException {
        int v = in.readInt();
        if (v > version)
            throw new java.io.IOException("Created with a version newer than this one... please upgrade.");
        matrix00 = in.readFloat();
        matrix01 = in.readFloat();
        matrix02 = in.readFloat();
        matrix10 = in.readFloat();
        matrix11 = in.readFloat();
        matrix12 = in.readFloat();
        matrix20 = in.readFloat();
        matrix21 = in.readFloat();
        matrix22 = in.readFloat();
    }

    public void debug() {
        System.out.println("Matrix :  " + pad(matrix00) + " " + pad(matrix10) + " " + pad(matrix20));
        System.out.println("          " + pad(matrix01) + " " + pad(matrix11) + " " + pad(matrix21));
        System.out.println("          " + pad(matrix02) + " " + pad(matrix12) + " " + pad(matrix22));
    }

    public String toString() {
        return "Matrix :  " + matrix00 + " " + matrix10 + " " + matrix20 + "      " + matrix01 + " " + matrix11 + " " + matrix21 + "      "
                + matrix02 + " " + matrix12 + " " + matrix22;
    }

    public void toMatrix() {
        System.out.println("r= r*" + matrix00 + " + g*" + matrix10 + " + b*" + matrix20);
        System.out.println("g= r*" + matrix01 + " + g*" + matrix11 + " + b*" + matrix21);
        System.out.println("b= r*" + matrix02 + " + g*" + matrix12 + " + b*" + matrix22);
    }

    private final static String pad(float f) {
        String s = f + "";
        while (s.length() < 12)
            s = " " + s;
        return s;
    }

    public Matrix3() {
        makeIdentity();
    }

    public Matrix3(Matrix3 o) {
        matrix00 = o.matrix00;
        matrix10 = o.matrix10;
        matrix20 = o.matrix20;
        matrix01 = o.matrix01;
        matrix11 = o.matrix11;
        matrix21 = o.matrix21;
        matrix02 = o.matrix02;
        matrix12 = o.matrix12;
        matrix22 = o.matrix22;
    }

    public void transpose() {
        Matrix3 o = new Matrix3();
        matrix00 = o.matrix00;
        matrix10 = o.matrix01;
        matrix20 = o.matrix02;
        matrix01 = o.matrix10;
        matrix11 = o.matrix11;
        matrix21 = o.matrix12;
        matrix02 = o.matrix20;
        matrix12 = o.matrix21;
        matrix22 = o.matrix22;
    }

    private static final float sin(float angle) {
        return (float) Math.sin(angle);
    }

    private static final float cos(float angle) {
        return (float) Math.cos(angle);
    }

    public void makeRotationAroundX(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        matrix00 = 1;
        matrix10 = 0;
        matrix20 = 0;
        matrix02 = 0;
        matrix11 = cos;
        matrix21 = sin;
        matrix02 = 0;
        matrix12 = -sin;
        matrix22 = cos;
    }

    public void makeRotation(float xa, float ya, float za) {
        float sinx = 0.0f, cosx = 1.0f, siny = 0.0f, cosy = 1.0f, sinz, cosz;
        sinz = sin(za);
        cosz = cos(za);
        if (xa != 0.0 || ya != 0.0) {
            sinx = sin(xa);
            cosx = cos(xa);
            siny = sin(ya);
            cosy = cos(ya);
        }
        matrix00 = cosy * cosz;
        matrix10 = cosy * sinz;
        matrix20 = -siny;
        matrix02 = sinx * siny * cosz - cosx * sinz;
        matrix11 = sinx * siny * sinz + cosx * cosz;
        matrix21 = sinx * cosy;
        matrix02 = cosx * siny * cosz + sinx * sinz;
        matrix12 = cosx * siny * sinz - sinx * cosz;
        matrix22 = cosx * cosy;
    }

    public void makeRotationAroundY(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        matrix00 = cos;
        matrix10 = 0;
        matrix20 = -sin;
        matrix02 = 0;
        matrix11 = 1;
        matrix21 = 0;
        matrix02 = sin;
        matrix12 = 0;
        matrix22 = cos;
    }

    public void makeRotationAroundZ(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        matrix00 = cos;
        matrix10 = sin;
        matrix20 = 0;
        matrix02 = -sin;
        matrix11 = cos;
        matrix21 = 0;
        matrix02 = 0;
        matrix12 = 0;
        matrix22 = 1;
    }

    public void makeTranslation(float x, float y) {
        matrix00 = 1;
        matrix10 = 0;
        matrix20 = 0;
        matrix02 = 0;
        matrix11 = 1;
        matrix21 = 0;
        matrix02 = x;
        matrix12 = y;
        matrix22 = 1;
    }

    public void makeScaling(float x, float y, float z) {
        matrix00 = x;
        matrix10 = 0;
        matrix20 = 0;
        matrix02 = 0;
        matrix11 = y;
        matrix21 = 0;
        matrix02 = 0;
        matrix12 = 0;
        matrix22 = z;
    }

    public void makeIdentity() {
        matrix00 = 1;
        matrix10 = 0;
        matrix20 = 0;
        matrix02 = 0;
        matrix11 = 1;
        matrix21 = 0;
        matrix02 = 0;
        matrix12 = 0;
        matrix22 = 1;
    }

    public Point3f transform(Point3f t) {
        return transform(t._a, t._b, t._c);
    }

    public Point3f transform(float x, float y, float z) {
        return new Point3f((x * matrix00 + y * matrix10 + z * matrix20), (x * matrix01 + y * matrix11 + z * matrix21), (x * matrix02 + y
                * matrix12 + z * matrix22));
    }

    public static void copy(Matrix3 src, Matrix3 dst) {
        dst.matrix00 = src.matrix00;
        dst.matrix01 = src.matrix01;
        dst.matrix02 = src.matrix02;
        dst.matrix10 = src.matrix10;
        dst.matrix11 = src.matrix11;
        dst.matrix12 = src.matrix12;
        dst.matrix20 = src.matrix20;
        dst.matrix21 = src.matrix21;
        dst.matrix22 = src.matrix22;
    }

    public void mul(Matrix3 matrix3f) {
        float f0 = matrix00 * matrix3f.matrix00 + matrix01 * matrix3f.matrix10 + matrix02 * matrix3f.matrix20;
        float f1 = matrix00 * matrix3f.matrix01 + matrix01 * matrix3f.matrix11 + matrix02 * matrix3f.matrix21;
        float f2 = matrix00 * matrix3f.matrix02 + matrix01 * matrix3f.matrix12 + matrix02 * matrix3f.matrix22;
        float f3 = matrix10 * matrix3f.matrix00 + matrix11 * matrix3f.matrix10 + matrix12 * matrix3f.matrix20;
        float f4 = matrix10 * matrix3f.matrix01 + matrix11 * matrix3f.matrix11 + matrix12 * matrix3f.matrix21;
        float f5 = matrix10 * matrix3f.matrix02 + matrix11 * matrix3f.matrix12 + matrix12 * matrix3f.matrix22;
        float f6 = matrix20 * matrix3f.matrix00 + matrix21 * matrix3f.matrix10 + matrix22 * matrix3f.matrix20;
        float f7 = matrix20 * matrix3f.matrix01 + matrix21 * matrix3f.matrix11 + matrix22 * matrix3f.matrix21;
        float f8 = matrix20 * matrix3f.matrix02 + matrix21 * matrix3f.matrix12 + matrix22 * matrix3f.matrix22;
        matrix00 = f0;
        matrix01 = f1;
        matrix02 = f2;
        matrix10 = f3;
        matrix11 = f4;
        matrix12 = f5;
        matrix20 = f6;
        matrix21 = f7;
        matrix22 = f8;
    }

    public void invert() {
        double ad[] = new double[9];
        double ad1[] = new double[9];
        int ai[] = new int[3];
        ad[0] = matrix00;
        ad[1] = matrix01;
        ad[2] = matrix02;
        ad[3] = matrix10;
        ad[4] = matrix11;
        ad[5] = matrix12;
        ad[6] = matrix20;
        ad[7] = matrix21;
        ad[8] = matrix22;
        if (!luDecomposition(ad, ai))
            throw new IllegalArgumentException("Matrix cannot be inverted.");
        for (int i = 0; i < 9; i++)
            ad1[i] = 0.0D;
        ad1[0] = 1.0D;
        ad1[4] = 1.0D;
        ad1[8] = 1.0D;
        luBacksubstitution(ad, ai, ad1);
        matrix00 = (float) ad1[0];
        matrix01 = (float) ad1[1];
        matrix02 = (float) ad1[2];
        matrix10 = (float) ad1[3];
        matrix11 = (float) ad1[4];
        matrix12 = (float) ad1[5];
        matrix20 = (float) ad1[6];
        matrix21 = (float) ad1[7];
        matrix22 = (float) ad1[8];
    }

    private static boolean luDecomposition(double ad[], int ai[]) {
        double ad1[] = new double[3];
        int i1 = 0;
        int i2 = 0;
        for (int i = 3; i-- != 0;) {
            double d = 0.0D;
            for (int k = 3; k-- != 0;) {
                double d1 = ad[i1++];
                d1 = Math.abs(d1);
                if (d1 > d)
                    d = d1;
            }
            if (d == 0.0D)
                return false;
            ad1[i2++] = 1.0D / d;
        }
        int l = 0;
        for (int j = 0; j < 3; j++) {
            for (int j1 = 0; j1 < j; j1++) {
                int j3 = l + 3 * j1 + j;
                double d2 = ad[j3];
                int k2 = j1;
                int i4 = l + 3 * j1;
                for (int l4 = l + j; k2-- != 0; l4 += 3) {
                    d2 -= ad[i4] * ad[l4];
                    i4++;
                }
                ad[j3] = d2;
            }
            double d4 = 0.0D;
            int j2 = -1;
            for (int k1 = j; k1 < 3; k1++) {
                int k3 = l + 3 * k1 + j;
                double d3 = ad[k3];
                int l2 = j;
                int j4 = l + 3 * k1;
                for (int i5 = l + j; l2-- != 0; i5 += 3) {
                    d3 -= ad[j4] * ad[i5];
                    j4++;
                }
                ad[k3] = d3;
                double d5;
                if ((d5 = ad1[k1] * Math.abs(d3)) >= d4) {
                    d4 = d5;
                    j2 = k1;
                }
            }
            if (j2 < 0)
                throw new IllegalArgumentException("Matrix cannot be inverted.");
            if (j != j2) {
                int i3 = 3;
                int k4 = l + 3 * j2;
                int j5 = l + 3 * j;
                while (i3-- != 0) {
                    double d6 = ad[k4];
                    ad[k4++] = ad[j5];
                    ad[j5++] = d6;
                }
                ad1[j2] = ad1[j];
            }
            ai[j] = j2;
            if (ad[l + 3 * j + j] == 0.0D)
                return false;
            if (j != 2) {
                double d7 = 1.0D / ad[l + 3 * j + j];
                int l3 = l + 3 * (j + 1) + j;
                for (int l1 = 2 - j; l1-- != 0;) {
                    ad[l3] *= d7;
                    l3 += 3;
                }
            }
        }
        return true;
    }

    private static void luBacksubstitution(double ad[], int ai[], double ad1[]) {
        int j1 = 0;
        for (int i1 = 0; i1 < 3; i1++) {
            int k1 = i1;
            int j = -1;
            for (int i = 0; i < 3; i++) {
                int k = ai[j1 + i];
                double d = ad1[k1 + 3 * k];
                ad1[k1 + 3 * k] = ad1[k1 + 3 * i];
                if (j >= 0) {
                    int l1 = i * 3;
                    for (int l = j; l <= i - 1; l++)
                        d -= ad[l1 + l] * ad1[k1 + 3 * l];
                } else if (d != 0.0D)
                    j = i;
                ad1[k1 + 3 * i] = d;
            }
            int i2 = 6;
            ad1[k1 + 6] /= ad[i2 + 2];
            i2 -= 3;
            ad1[k1 + 3] = (ad1[k1 + 3] - ad[i2 + 2] * ad1[k1 + 6]) / ad[i2 + 1];
            i2 -= 3;
            ad1[k1 + 0] = (ad1[k1 + 0] - ad[i2 + 1] * ad1[k1 + 3] - ad[i2 + 2] * ad1[k1 + 6]) / ad[i2 + 0];
        }
    }
    //{{DECLARE_CONTROLS
    //}}
}
