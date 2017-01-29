package telegramBotExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.vandenham.telegram.botapi.CommandHandler;
import co.vandenham.telegram.botapi.MessageHandler;
import co.vandenham.telegram.botapi.TelegramBot;
import co.vandenham.telegram.botapi.types.Message;

public class Bot extends TelegramBot {

	private static final String ACCESS_TOKEN = "307781727:AAHqTEIUAM9AcGqfBUqDMGOLhx3IAX0VU7A";

	private List<Event> userEvents;

	public Bot(String botToken) {

		super(botToken);

	}

	@CommandHandler({ "start" })
	public void handleStart(Message message) {

		String response = "Hola, soy un bot que registra tus entradas y salidas en la oficina \n";
		response += "Puedes utilizar este comando para recuperar tus eventos: /eventos \n";

		userEvents = new ArrayList<>();

		replyTo(message, response);
		return;
	}

	@CommandHandler({ "eventos" })
	public void handleList(Message message) {

		String response = "Estos son tus eventos: \n";

		for (Event event : userEvents) {
			response += "- " + event.getDate() + " - " + event.getType() + " - " + event.getUser() + "\n";
		}

		replyTo(message, response);
		return;
	}

	@MessageHandler(contentTypes = Message.Type.TEXT)
	public void handleTextMessages(Message message) {

		if (message.getText().toLowerCase().contains("entro") || message.getText().toLowerCase().contains("entrando")) {
			Event event = new Event(new Date(), message.getFrom().getUsername(), "Entrada");
			userEvents.add(event);
			replyTo(message, "Registraré tu entrada");
			return;
		}

		if (message.getText().toLowerCase().contains("salgo") || message.getText().toLowerCase().contains("saliendo")) {
			Event event = new Event(new Date(), message.getFrom().getUsername(), "Salida");
			userEvents.add(event);
			replyTo(message, "Registraré tu salida");
			return;
		}

		replyTo(message, "No te he entendido");
		return;
	}

	@MessageHandler(contentTypes = Message.Type.LOCATION)
	public void handleLocationMessages(Message message) {

		replyTo(message, "Longitud " + message.getLocation().getLongitude() + " - Latitud: "
				+ message.getLocation().getLatitude());
	}

	public static void main(String[] args) {

		TelegramBot bot = new Bot(ACCESS_TOKEN);
		bot.start();

	}

}
