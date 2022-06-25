from kiteconnect import KiteTicker
import pandas as pd

token = input("Enter token:")
kws = KiteTicker("ol0rnn4qxfcrn6vf", token)

data_dict = {
    11874306: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11874562: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11875330: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11876098: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11878402: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11879170: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11879938: {'last_price': [], 'last_quantity': [], 'timestamp': []},
    11880194: {'last_price': [], 'last_quantity': [], 'timestamp': []}
}

def on_ticks(ws, ticks):
    global data_dict
    for tick in ticks:
        data_dict[tick['instrument_token']]['last_price'].append(tick['last_price'])
        data_dict[tick['instrument_token']]['last_quantity'].append(tick['last_quantity'])
        data_dict[tick['instrument_token']]['timestamp'].append(tick['timestamp'])
    #print(data_dict)

def on_connect(ws, response):
    ws.subscribe([11874306, 11874562, 11875330, 11876098, 11878402, 11879170, 11879938, 11880194])
    ws.set_mode(ws.MODE_FULL, [11874306, 11874562, 11875330, 11876098, 11878402, 11879170, 11879938, 11880194])

def on_close(ws, code, reason):
    writer = pd.ExcelWriter("trade_sheet.xlsx")
    for key, value in data_dict.items():
        df = pd.DataFrame(value)
        df.to_excel(writer, sheet_name=str(key))
    writer.save()
    writer.close()
    ws.stop()

kws.on_ticks = on_ticks
kws.on_connect = on_connect
kws.on_close = on_close

kws.connect()

