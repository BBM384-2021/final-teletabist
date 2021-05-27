<template>
    <div id="chart">
        <apexchart type="pie" width="380" :options="chartOptions" :series="series"></apexchart>
    </div>
    
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
export default {
 components: {
    apexchart : VueApexCharts,
 },
 props : {
     interest : {type:Array, default : () => {return []}},

 },
 data: function() {
    return{
    series: [],
    
    chartOptions: {
        title: {
            text: "Interests"
        },
        chart: {
        width: 380,
        type: 'pie',
        },
        labels: [],
         dataLabels: {
            formatter(val, opts) {
            const name = opts.w.globals.labels[opts.seriesIndex]
            return [name, val.toFixed(1) + '%']
            }
        },
        legend: {
            show: false
        },
        responsive: [{
        breakpoint: 480,
        
        options: {
            chart: {
            width: 200
            },
            
        }
        }]},
       
    }
},
mounted() {
    this.interest.forEach(i => {
        this.chartOptions.labels.push(i.name);
        this.series.push(i.ratio);
    });
},
// watch : {
//     interest : function(val){
//         console.log("WORKKKKKK");
//         let arrLabel  = new Array();
//         let arrSeries = new Array();
//         val.forEach(i => {
//             console.log("workkk");
//             arrLabel.push(i.name);
//             arrSeries.push(i.ratio);
//             console.log(i.name);
//         });
//         this.chartOptions.labels = arrLabel;
//         this.series = arrSeries;
//     } 

//  }
 
}
</script>

<style>

</style>