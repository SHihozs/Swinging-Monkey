package com.company;

import java.util.*;

class Tree
{
    private int treeNumber;
    private int height;

    public Tree(int treeNumber,int height)
    {
        this.treeNumber = treeNumber;
        this.height     = height;
    }

    public int getTreeNumber()  { return treeNumber; }
    public int getHeight()      { return height; }
}

class CountTreePair
{
    private Stack<Tree> treeStack = new Stack<Tree>();
    private int pair;

    public CountTreePair()
    {
        this.pair = 0;
    }

    public void solution(ArrayList<Tree> treeArrayList)
    {
        for (int i=0 ; i<treeArrayList.size() ; i++)
        {
            Tree tree = treeArrayList.get(i);

            if (treeStack.isEmpty())
            {
                treeStack.push(treeArrayList.get(i));
            }
            else
            {
                while (!treeStack.isEmpty() && (treeStack.peek().getHeight() < tree.getHeight()))
                {
                    pair++;
                    System.out.printf("%2d  Tree (%2d) : %2d ft ---> Tree (%2d) : %2d ft\n", pair, treeStack.peek().getTreeNumber(), treeStack.peek().getHeight(), i+1, tree.getHeight());
                    treeStack.pop();
                }
                if (treeStack.isEmpty())
                {
                    treeStack.push(tree);
                }
                else
                {
                    if (treeStack.peek().getHeight() > tree.getHeight())
                    {
                        pair++;
                        System.out.printf("%2d  Tree (%2d) : %2d ft ---> Tree (%2d) : %2d ft\n", pair, treeStack.peek().getTreeNumber(), treeStack.peek().getHeight(), i+1 , tree.getHeight());
                        treeStack.push(tree);

                    }
                    else if (treeStack.peek().getHeight() == tree.getHeight())
                    {
                        pair++;
                        System.out.printf("%2d  Tree (%2d) : %2d ft ---> Tree (%2d) : %2d ft\n", pair, treeStack.peek().getTreeNumber(), treeStack.peek().getHeight(), i+1, tree.getHeight());
                        treeStack.pop();
                        treeStack.push(tree);
                    }

                }
            }
        }
    }

    public void printPair()
    {
        System.out.println("------------------------------------------------------------");
        System.out.println("TOTAL TREE-PAIRS : " + this.pair);
        System.out.println("============================================================");
    }
}

public class App
{
    public int ReceiveNumberOfTree()
    {
        boolean redo = true;
        int value    = 0;

        while (redo)
        {
            try
            {
                Scanner input = new Scanner(System.in);
                System.out.println("NUMBER OF TREE : ");
                if (input.hasNext())
                {
                    value = input.nextInt();
                    if (value < 3)
                    {
                        throw new InputMismatchException();
                    }
                    else
                    {
                        redo = false;
                    }
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println("============================================================");
                System.out.println("[INPUT MISMATCH EXCEPTION]");
                System.out.println("============================================================");
            }
            catch (NumberFormatException e)
            {
                System.out.println("============================================================");
                System.out.println("[NUMBER FORMAT EXCEPTION]");
                System.out.println("============================================================");
            }
        }

        return value;
    }

    public int ReceiveHeight(int i)
    {
        boolean redo = true;
        int value    = 0;

        while (redo)
        {
            try
            {
                Scanner input = new Scanner(System.in);
                System.out.println("Height of Tree (" + i + ") : ");
                if (input.hasNext())
                {
                    value = input.nextInt();
                    if (value < 1)
                    {
                        throw new InputMismatchException();
                    }
                    else
                    {
                        redo = false;
                    }
                }
                else
                {
                    input.next();
                    continue;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("============================================================");
                System.out.println("[INPUT MISMATCH EXCEPTION]");
                System.out.println("============================================================");
            }
            catch (NumberFormatException e)
            {
                System.out.println("============================================================");
                System.out.println("[NUMBER FORMAT EXCEPTION]");
                System.out.println("============================================================");
            }
        }

        return value;
    }

    public boolean RepeatOrNot()
    {
        boolean redo = true;

        while(redo)
        {
            try
            {
                Scanner input = new Scanner(System.in);
                String  in;

                System.out.println("DO YOU WANT TO TRY AGAIN? (Y/N) : ");

                if(input.hasNext())
                {
                    in = input.nextLine();
                    if(in.equalsIgnoreCase("Y"))
                    {
                        redo = false;
                        return true;
                    }
                    else if(in.equalsIgnoreCase("N"))
                    {
                        redo = false;
                        return false;
                    }
                    else
                    {
                        throw new InputMismatchException();
                    }
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("============================================================");
                System.out.println("[INPUT MISMATCH EXCEPTION]");
                System.out.println("============================================================");
            }
            catch (NumberFormatException e)
            {
                System.out.println("============================================================");
                System.out.println("[NUMBER FORMAT EXCEPTION]");
                System.out.println("============================================================");
            }
        }

        return false;
    }

    public static void main(String[] args)
    {
        boolean running = true;
        App app         = new App();

        while(running)
        {

            int numberOfTree                = 0;
            CountTreePair CTP               = new CountTreePair();
            ArrayList<Tree> treeArrayList   = new ArrayList<>();

            //==============================[GET INPUT]==============================//

            System.out.println("============================================================");
            numberOfTree = app.ReceiveNumberOfTree();
            for (int i=0 ; i<numberOfTree ; i++)
            {
                treeArrayList.add(new Tree(i+1,app.ReceiveHeight(i+1)));
            }
            System.out.println("============================================================");
            System.out.println();

            //==============================[SOLUTION]==============================//

            System.out.println("=========================[SOLUTION]=========================");

            CTP.solution(treeArrayList);
            CTP.printPair();

            //==============================[REPEAT?]==============================//

            running = app.RepeatOrNot();
            System.out.println("============================================================");
        }
    }
}
