public class Fun {

    public String card_conv(String number)
    {
        char[] old_arr = number.toCharArray();
        int index = 0;
        for (char value : old_arr)
        {
            if (index > 11) {
                old_arr[index] = value;
            }
            else {
                old_arr[index] = 'x';
            }
            index++;
        }
        return String.valueOf(old_arr);
    }

    public static void main(String[] args)
    {
        Fun fun = new Fun();
        System.out.println(fun.card_conv("1234567890123456"));
    }
}
