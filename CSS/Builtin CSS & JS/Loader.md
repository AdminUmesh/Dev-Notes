**Loader before data fetch**

```html
<!-- loader section -->
<div class="container-fluid loader-wrap">
    <div class="row h-100">
        <div class="col-10 col-md-6 col-lg-5 col-xl-3 mx-auto text-center align-self-center">
            <div class="logo-wallet">
                <div class="wallet-bottom">
                </div>
                <div class="wallet-cards"></div>
                <div class="wallet-top">
                </div>
            </div>
            <p class="mt-4">
                <span class="text-secondary">E Invoicing Sync</span><br><strong>
                    Please
                    wait...
                </strong>
            </p> 
        </div>
    </div>
</div>
```

```javaScript
//jquery
function Getdata() {
   // Loader Start
    $('.loader-wrap').fadeIn('fast');

    $.ajax({
        url: "../Home/gstinvoicedata?fromdate=" + fromdate + "&todate=" + todate + "&mod=" + mod + "&typ=" + typ,
        dataType: "json",
        type: 'GET',
        success: function (result) {
            var data = result;

            $.ajax({
                url: "../Home/StatusIRN?fromdate=" + fromdate + "&todate=" + todate + "&mod=" + mod + "&data=" + typ,
                type: "GET",
                success: function (result) {
                    var data = result;
                    for (i = 0; i < data.length; i++) {
                        let item1 = data[i];

                        let DoneIRN = item1.DoneIRN;
                        let Pending = item1.Pending;
                        let total = item1.total;

                        $("#done").val(DoneIRN);
                        $("#pending").val(Pending);
                        $("#totalbill").val(total);

                    }


                },
                error: function (err) {
                    alert("Auth Key Expired Relogin Again!!!")
                }
            });

            for (i = 0; i < data.IRN; i++) {
                let countirn = data[i];


            }

            for (i = 0; i < data.length; i++) {
                let item = data[i];

                let bill = "'" + item.BillNo + "'";
                


                $("#databind").append('<tr> <td><input type="radio" name="optradio" onclick="checkdata(' + bill + ');"></td><td>' + item.BillNo + '</td><td>' + item.Closedate + '</td><td>' + item.Guest_Code + ' </td><td>' + item.Comp_Name + ' </td><td>' + item.BillAmt + '</td><td>' + item.Status + '</td><td>' + item.IRN + '</td></tr>')

            }
            // Loader end
            $('.loader-wrap').fadeOut('fast');
        },

    });
}
```