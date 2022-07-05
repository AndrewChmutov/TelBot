import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class MainBot extends TelegramLongPollingBot{
	
	
	public void onUpdateReceived(Update update)
	{
		if(update.hasMessage())
		{
			try {
				handleMessage(update.getMessage());
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleMessage(Message message) throws TelegramApiException
	{
		if (message.hasText() && message.hasEntities())
		{
			Optional<MessageEntity> commandEntity =
					message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
			if(commandEntity.isPresent())
			{
				String command = message.getText().substring(
						commandEntity.get().getOffset(), commandEntity.get().getLength());
				
				switch(command)
				{
					case "/choose_object":
//						List<List<InlineKeyboardButton>> buttons= new ArrayList<>();
//						buttons.add(
//								 
//						)
						
						execute(SendMessage.builder().text("Choose type of stalker::").chatId(message.getChatId().toString()).build());
				}
				return;
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
		TelegramBotsApi tba = new TelegramBotsApi(DefaultBotSession.class);
		tba.registerBot(bot);
	}
}
