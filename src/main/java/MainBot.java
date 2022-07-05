import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
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
		if(update.hasCallbackQuery())
		{
			try
			{
				handleCallbackquery(update.getCallbackQuery());
			}
			catch(TelegramApiException e)
			{
				e.printStackTrace();
			}
		}
		else if (update.hasMessage())
		{
			try
			{
				handleMessage(update.getMessage());
			}
			catch(TelegramApiException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void handleMessage(Message message) throws TelegramApiException
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
						List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
						
						buttons.add(Arrays.asList(InlineKeyboardButton.builder().text("Free stalker").callbackData("Free stalker").build()));
						buttons.add(Arrays.asList(InlineKeyboardButton.builder().text("Duty").callbackData("Duty").build()));
						buttons.add(Arrays.asList(InlineKeyboardButton.builder().text("Freedom").callbackData("Freedom").build()));
						buttons.add(Arrays.asList(InlineKeyboardButton.builder().text("Mutant").callbackData("Mutant").build()));
						buttons.add(Arrays.asList(InlineKeyboardButton.builder().text("Everyone").callbackData("Everyone").build()));
				
						
						execute(SendMessage.builder()
								.text("Choose type of story:")
								.chatId(message.getChatId().toString())
								.replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
								.build());
				}
				return;
			}
		}
	}
	
	private void handleCallbackquery(CallbackQuery callbackquery) throws TelegramApiException
	{
		Message message = callbackquery.getMessage();
		String data = callbackquery.getData();
		execute(SendMessage.builder().text(data).chatId(message.getChatId().toString()).build());
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
