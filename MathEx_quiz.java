package math1;

public class MathEx_quiz {
    public static void main(String[] args) {
        // 로또 번호 생성기

        for (int i = 1; i <= 30 ; i++) {

            if ( i % 6 == 1 ) {
                System.out.print( ( i / 6 + 1 ) + "회" );
            }

            int a = (int)( Math.random() * 45 ) + 1;
            System.out.print( ( a < 10 ) ? "  " + a : " " + a );

            if ( i % 6 == 0 && i != 30 ) {
                System.out.println();
            }

        }

    }
}