public class Main {
    public static void main(String[] args) {
        long pieces[] = {0,0,0,0,3};
        int instructions[][] = {{0,1,3}, {1,4,1}, {2,4,1}, {3,4,2}};
        for (int i = 0; i < pieces.length; i++){
            pieces = A3Q2.num_pieces(pieces,instructions);
            System.out.println(pieces[i]);
        }

    }
}