package pa1pal.sendbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import pa1pal.sendbutton.lib.*;

public class MainActivity extends AppCompatActivity
{
	@Bind(R.id.send_button)
	pa1pal.sendbutton.lib.SendButton send_button;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}


}
