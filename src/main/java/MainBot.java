import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;



public class MainBot extends TelegramLongPollingBot{
	
	
	public void onUpdateReceived(Update update)
	{
		if(update.hasMessage())
		{
			handleMessage(update.getMessage());
		}
	}
	
	public void handleMessage(Message message)
	{
		if (message.hasText() && message.hasEntities())
		{
			Optional<MessageEntity> commandEntity =
					message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
			if(commandEntity.isPresent())
			{
				
			}
		}
	}
	
	public String getBotUsername()
	{
		return "https://t.me/Anekdot_stalker_bot";
	}
	
	public String getBotToken()
	{
		return Tokener.getSafeToken();
	}
	
	public static void main(String[] args) throws Exception
	{
		MainBot bot = new MainBot();
		//bot.execute(SendMessage.builder().chatId("597587192").text("Hello world!").build());
		
	}
}
