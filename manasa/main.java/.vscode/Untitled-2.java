import java.awt.*;
import javax.swing.*;

class layoptdemo
{
    p s v main( String a[] )
    {
        JFrame frame = new JFrame("flowlayout");
        frame.setLayout( new FlowLayout( FlowLayout.LEFT, 10, 10 ) );

        JButton btn[] = new JButton[]
        {
            ("one"),
            ("two"),
            ("third"),
            ("fourth"),
            ("fifth"),
        };

        frame.add( btn[0] );
        frame.add( btn[1] );
        frame.add( btn[2] );
        frame.add( btn[3] );
        frame.add( btn[4] );

        frame.setSize( 400, 300 );
        frame.setVisible( true );
    }
}