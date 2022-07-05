import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MainBot extends DefaultAbsSender{
	public MainBot(DefaultBotOptions options) { super(options); }
	
	public String getBotToken()
	{
		return Tokener.getSafeToken();
	}
	
	public static void main(String[] args) throws Exception
	{
		MainBot bot = new MainBot(new DefaultBotOptions());
		bot.execute(SendMessage.builder().chatId("597587192").text("Hello world!").build());
		
	}
}
