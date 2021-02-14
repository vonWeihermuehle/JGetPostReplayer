package net.mbmedia;

import net.mbmedia.JGetPostReplayer.JGetPostReplayer;
import net.mbmedia.JGetPostReplayer.Protocol;

public class Main {

    public static void main(String[] args) {

    	JGetPostReplayer j = new JGetPostReplayer("C:\\Users\\Max\\Desktop\\log-files",
				500, //millis
				500, //millis
				Protocol.HTTPS,
				"bootsfreund.de",
				443);

    	j.start();


	}
}
