/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Pawel
 */
public class Perceptron {

    // konfiguracja Perceptronu
    //public int krok;
    
    public int n;          // liczba wejść, przy n+1 wagach

    public double s;                // sygnał
    
    public double y;                // wyjście

    public int f;                   // 1 - unipolarna funkcja, -1 - bipolarna funkcja

    public double[] w;              // tablica wag, pamięć perceptronu

// elementy związane z uczeniem:
    public double[][] iSetX;        // tablica tablic (wektro wektorów) wejściowych ze zbioru uczącego (kolumny: x0,x1,...,xn)

    public double[] iSetD;          // tablica (wektor) wyjść wzorccowych dla wektorów wejściowych ze zbioru ucz. (kolumna: d)

    public int m;                   // liczba danych w zbiorze uczącym (liczba par (x,d) )

    public double r;                // Ro, współczynnik uczenia

    public int t;                   // informacja o numerze kroku uczenia, startowo t = 0;
    
    public int k;   

// zachowanie Perceptronu:
    public Perceptron(double[] _w, int _n) {
        this.n = _n;
        this.w = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            this.w[i] = _w[i];
            System.out.println("i : " + i );
        }
        
        
    }
    
    public Perceptron(int _n, int _f, double[] _w, double[][] _iSetX, 
            double[] _iSetD, int _m, double _r) {
        // wartościowanie pól:
        this.n = _n;
        this.r = _r;
        this.f = _f; // 1 - unipoler, -1 - bipolar
        this.m = _m;
        this.k = 0;
        this.s = 0;
        this.y = _f;

        // inicjacja zbioru uczącego
        this.iSetX = new double[m][];
        this.iSetD = new double[m];

        for (int i = 0; i < this.m; i++) {
            this.iSetX[i] = new double[n + 1];
            for (int j = 0; j < this.n + 1; j++) {
                this.iSetX[i][j] = _iSetX[i][j];
            }
            this.iSetD[i] = _iSetD[i];
        }

        this.w = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            this.w[i] = _w[i];
        }
    }

    // metody ComputeS i ComputeY obliczają sygnał i 
    //wyjście i zapisują do pól s i y !!!
    public double ComputeS() {
        
        s = 0;
        for (int i = 0; i < this.n + 1; i++) {
            s += this.iSetX[k][i] * this.w[i];
        }
        return s; 
    }
    
    public double ComputeY(){
        return 0;
    }
    // metoda wykonująca jeden krok procesu uczenia	
    public void LearningStep() {
        
        t++;
        
        if (Test() != 0) {
            for (int i = 0 ; i < this.n + 1; i++) {
                this.w[i] = this.w[i] + this.r * (this.iSetD[k] - this.y) * this.iSetX[k][i];
                
            }
        }
        
        System.out.println("t = " + k);
        if (k == 3){
                k = 0;
            } else {
                k++;
        }
        
    }

    // metoda resetuje proces uczenia (reset pola t oraz losowanie wag?)
    public void ResetLearningProcess() {
        k = 0;
        DrawWeight(0, 5);
    }

    // metoda wyznaczająca wartość funkcji aktywacji dla zadanej wartości, 
    //metoda nie zmienia stanu klasy (w odróżnieniu od metody ComputeY(double[] _x))
    
    public double Y() {
        if (this.f == 1) {
            if (s > 0) {
                y = 1;
            } else if (s <= 0) {
                y = 0;
            }
        }else if (this.f == -1){
            for (int i = 0; i < this.n; i++) {
                if (s > 0) {
                    y = 1;
                } else if (s <= 0) {
                    y = -1;
                }
            }
        }
        return y;
    }

    // metoda testująca Perceptron, zwraca liczbę niezgodnych wyjść dla zboru uczącego
    // 0 na wyjściu oznacza, że neruon nauczył się już funkcji ze zbioru uczącego
    public int Test() {
            
        
        
        if (this.iSetD[k] == y) {
            return 0;
        } else {
            return 1;
        }

    }
        

    // metoda losująca wagi o wartościach z przedziału [a;b]
    public void DrawWeight(double a, double b) {
        
        
        double los; 
        for (int i = 0; i < n + 1; i++) {
            Random random = new Random();
            los = random.nextDouble() * (b - a) + a;
            //los = random.nextInt(5);
            this.w[i] = los;
            System.out.println("w [ " + i + " ] = " + w[i]);
        }
    }

// metoda pozwalająca na konwersją obiektu klasy Perceptron na łańcuch znaków (na potrzeby wyświetlania stanu obiektu)
    
    @Override
    public String toString() {
        StringBuilder stan = new StringBuilder("STAN PERCEPRTONU: \n");
        stan.append("N=" + n + ",\ns=" + s + ",\ny=" + y + ",\n");
        stan.append("Funkcja aktywacji: " + (f == 1?"Unipolarna\n":"Bipolarna\n"));
        stan.append("Wagi: \n");
        for (int i=0; i<w.length; ++i) {
            stan.append("" + w[i] + "\n");
        }
        stan.append("Zbior uczacy dla funkcji: \n");
        stan.append("x0   x1   x2   d\n");
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n + 1; j++) {
                stan.append("" + this.iSetX[i][j] + "  ");
            }
            stan.append("" + this.iSetD[i] + "  \n");
        }
        return stan.toString();
    }
    
    
    public void wypisanieNowychWag(){
        for (int i = 0; i < n + 1; i++){
            System.out.print(" " + w[i]);
        }
        System.out.println("");
    }
    
    public double [] getWag(){
        
        return w;
        
    }
    
    public double wypisanieD(){
        return this.iSetD[k];
    }
    
    public double wypisanieT(){
        return this.k;
    }
}
